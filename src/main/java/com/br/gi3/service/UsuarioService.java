package com.br.gi3.service;

import com.br.gi3.model.Role;
import com.br.gi3.model.Usuario;
import com.br.gi3.repository.RoleRepository;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    private final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        log.debug("Request to post save Usuario");

        UsuarioDTO novoUsuario = new UsuarioDTO();
        novoUsuario.setUsername(usuarioDTO.getUsername());
        novoUsuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        Set<Role> roles = usuarioDTO.getRoles().stream()
                .map(nome -> roleRepository.findById(nome)
                        .orElseThrow(() -> new RuntimeException("Role não encontrada: " + nome)))
                .collect(Collectors.toSet());
        Usuario usuario = usuarioMapper.toEntity(novoUsuario);
        usuario.setRoles(roles);
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

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> searchByKeyword(String param, Pageable pageable) {
        log.debug("Request to get all username");
        return usuarioRepository.searchByKeyword(param, pageable).map(UsuarioMapper::toDto);
    }
}
