package com.br.gi3.service;

import com.br.gi3.model.Usuario;
import com.br.gi3.repository.UsuarioRepository;
import com.br.gi3.service.dto.UsuarioDTO;
import com.br.gi3.service.mapper.UsuarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioMapper usuarioMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        UsuarioDTO newUser = new UsuarioDTO();
        newUser.setUsername("newuser");
        newUser.setPassword("rawpassword");

        when(passwordEncoder.encode("rawpassword")).thenReturn("encodedpassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMapper.toEntity(newUser));

        UsuarioDTO savedUser = usuarioService.save(newUser);

        assertNotNull(savedUser);
        assertEquals("newuser", savedUser.getUsername());
        verify(passwordEncoder, times(1)).encode("rawpassword");
    }

    @Test
    void delete() {
        usuarioService.delete(1L);
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void update() {
        Usuario existingUser = new Usuario();
        existingUser.setId(1L);
        existingUser.setUsername("olduser");
        existingUser.setPassword("oldpassword");

        Usuario updatedDetails = new Usuario();
        updatedDetails.setId(1L);
        updatedDetails.setUsername("updateduser");
        updatedDetails.setPassword("newrawpassword");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode("newrawpassword")).thenReturn("newencodedpassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(existingUser);

        usuarioService.update(usuarioMapper.toDto(updatedDetails));
    }

    @Test
    void findOne() {
        Usuario entity = new Usuario();
        entity.setId(1L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(entity));
        Optional<UsuarioDTO> result = usuarioService.findOne(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Usuario entity = new Usuario();
        entity.setId(1L);
        entity.setUsername("user1");

        Page<Usuario> page = new PageImpl<>(List.of(entity));
        when(usuarioRepository.findAll(pageable)).thenReturn(page);
        Page<UsuarioDTO> result = usuarioService.findAll(pageable);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void findByUsername() {
        Usuario entity = new Usuario();
        entity.setUsername("user");

        when(usuarioRepository.findByUsername("user")).thenReturn(Optional.of(entity));
        Optional<UsuarioDTO> result = usuarioService.findByUsername("user");
        assertTrue(result.isPresent());
        assertEquals("user", result.get().getUsername());
    }

}