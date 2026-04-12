package com.br.gi3.service;

import com.br.gi3.model.Usuario;
import com.br.gi3.repository.UsuarioRepository;
import com.br.gi3.service.dto.UsuarioDTO;
import com.br.gi3.service.mapper.UsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    private final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        log.debug("Request to post save Usuario");

        UsuarioDTO novoUsuario = new UsuarioDTO();
        novoUsuario.setUsername(usuarioDTO.getUsername());
        novoUsuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword())); // Criptografa a senha
        novoUsuario.setType(usuarioDTO.getType());

        Usuario usuario = usuarioMapper.toEntity(novoUsuario);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public void delete(Long id) {
        log.debug("Request to delete Usuario by id : {}", id);
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO update(UsuarioDTO usuarioDTO) {
        log.debug("Request to update Usuario: {}", usuarioDTO);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findOne(Long id) {
        log.debug("Request to get one Usuario by id");
        return usuarioRepository.findById(id).map(UsuarioMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Usuario");
        return usuarioRepository.findAll(pageable).map(UsuarioMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByUsername(String username) {
        log.debug("Request to get one Usuario by username");
        return usuarioRepository.findByUsername(username).map(UsuarioMapper::toDto);
    }

}
