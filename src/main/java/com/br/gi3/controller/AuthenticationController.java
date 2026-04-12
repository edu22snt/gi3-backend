package com.br.gi3.controller;

import com.br.gi3.security.jwt.MyUserDetailsService;
import com.br.gi3.security.jwt.AuthenticationRequest;
import com.br.gi3.security.jwt.AuthenticationResponse;
import com.br.gi3.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil tokenProvider;
    private final MyUserDetailsService myUserDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil tokenProvider, MyUserDetailsService myUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
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
        final String jwt = tokenProvider.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/hello")
    public String hello() {
        return "Hello World com JWT!";
    }
}
