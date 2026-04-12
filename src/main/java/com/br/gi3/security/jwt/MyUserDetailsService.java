package com.br.gi3.security.jwt;

import com.br.gi3.model.Usuario;
import com.br.gi3.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

//    private final PasswordEncoder passwordEncoder;
//
//    // Simulação de utilizadores em memória
//    private final Map<String, String> users = new HashMap<>();
//
//    public MyUserDetailsService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//        // Adicionar um utilizador de exemplo
//        users.put("user", passwordEncoder.encode("password"));
//        users.put("admin", passwordEncoder.encode("adminpass"));
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (users.containsKey(username)) {
//            return new User(username, users.get(username), new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("Utilizador não encontrado: " + username);
//        }
//    }

    private UsuarioRepository usuarioRepository;

    MyUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // Retorna um objeto User do Spring Security com os dados do usuário do banco
        return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    }
}
