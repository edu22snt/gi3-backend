package com.br.gi3.controller;

import com.br.gi3.model.Usuario;
import com.br.gi3.repository.UsuarioRepository;
import com.br.gi3.service.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioRepository.findByUsername(usuarioDTO.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome de usuário já existe!");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsername(usuarioDTO.getUsername());
        novoUsuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword())); // Criptografa a senha
        novoUsuario.setType(usuarioDTO.getType());

        usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
    }
}
