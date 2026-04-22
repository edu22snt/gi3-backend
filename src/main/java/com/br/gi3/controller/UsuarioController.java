package com.br.gi3.controller;

import com.br.gi3.service.UsuarioService;
import com.br.gi3.service.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private UsuarioService service;

    UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody UsuarioDTO usuarioDTO) {
        if (service.findByUsername(usuarioDTO.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome de usuário já existe!");
        }
        service.save(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
    }

    @GetMapping("/findById/{id}")
    public Optional<UsuarioDTO> findById(@PathVariable Long id) {
        Optional<UsuarioDTO> usuario = service.findOne(id);
        return usuario;
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<UsuarioDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/searchByKeyword")
    public ResponseEntity<Page<UsuarioDTO>> searchByKeyword(@RequestParam String param, Pageable pageable) {
        return ResponseEntity.ok(service.searchByKeyword(param, pageable));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<UsuarioDTO> update(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO result = service.update(usuarioDTO);
        return ResponseEntity.ok().body(result);
    }

}
