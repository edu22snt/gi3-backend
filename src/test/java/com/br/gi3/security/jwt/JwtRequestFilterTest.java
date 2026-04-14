package com.br.gi3.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtRequestFilterTest {

    @Mock
    private MyUserDetailsService userDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;

    private UserDetails userDetails;

    @BeforeEach
    public void setUp( ) {
        MockitoAnnotations.openMocks(this);
        userDetails = new User("testuser", "password", Collections.emptyList());
        cleanContext();
    }

    /**
     * Limpar o contexto de segurança antes de cada teste.
     */
    private void cleanContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void doFilterInternal_validJwtToken() throws ServletException, IOException {
        String jwtToken = "Bearer valid.jwt.token";
        when(request.getHeader("Authorization")).thenReturn(jwtToken);
        when(jwtUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtUtil.validateToken("valid.jwt.token", userDetails)).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void doFilterInternal_noJwtToken() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void doFilterInternal_invalidJwtToken() throws ServletException, IOException {
        String jwtToken = "Bearer invalid.jwt.token";
        when(request.getHeader("Authorization")).thenReturn(jwtToken);
        when(jwtUtil.extractUsername("invalid.jwt.token")).thenReturn("testuser");
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtUtil.validateToken("invalid.jwt.token", userDetails)).thenReturn(false);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void doFilterInternal_jwtTokenWithoutBearerPrefix() throws ServletException, IOException {
        String jwtToken = "invalid.jwt.token"; // Missing Bearer prefix
        when(request.getHeader("Authorization")).thenReturn(jwtToken);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }


}