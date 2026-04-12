package com.br.gi3.security.jwt;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    // Simulação de utilizadores em memória
    private final Map<String, String> users = new HashMap<>();

    public MyUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        // Adicionar um utilizador de exemplo
        users.put("user", passwordEncoder.encode("password"));
        users.put("admin", passwordEncoder.encode("adminpass"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (users.containsKey(username)) {
            return new User(username, users.get(username), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Utilizador não encontrado: " + username);
        }
    }
}
