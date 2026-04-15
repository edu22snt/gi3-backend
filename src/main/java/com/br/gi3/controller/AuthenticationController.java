package com.br.gi3.controller;

import com.br.gi3.security.jwt.MyUserDetailsService;
import com.br.gi3.security.jwt.AuthenticationRequest;
import com.br.gi3.security.jwt.AuthenticationResponse;
import com.br.gi3.security.jwt.JwtUtil;
import com.br.gi3.service.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService myUserDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, MyUserDetailsService myUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.myUserDetailsService = myUserDetailsService;
    }

    @PostMapping()
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciais incorretas", e);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login (@RequestBody UsuarioDTO dto) {
//        Authentication auth =  authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        dto.getUsername(),
//                        dto.getPassword()
//                )
//        );
//        UserDetails user = (UserDetails) auth.getPrincipal();
//        String token = jwtUtil.generateToken(user);
//        return ResponseEntity.ok(Map.of("token", token));
//    }

}
