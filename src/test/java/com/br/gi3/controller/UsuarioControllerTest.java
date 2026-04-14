package com.br.gi3.controller;

import com.br.gi3.service.UsuarioService;
import com.br.gi3.service.dto.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService service;

    @InjectMocks
    private UsuarioController usuarioController;

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setUsername("testuser");
        usuarioDTO.setPassword("password");
    }

    @Test
    void shouldReturnCreatedWhenUserDoesNotExist() {
        when(service.findByUsername(anyString())).thenReturn(Optional.empty());
        when(service.save(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        ResponseEntity<String> response = usuarioController.save(usuarioDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Usuário registrado com sucesso!", response.getBody());
        verify(service, times(1)).findByUsername(anyString());
        verify(service, times(1)).save(any(UsuarioDTO.class));
    }

    @Test
    void shouldReturnConflictWhenUserAlreadyExists() {
        when(service.findByUsername(anyString())).thenReturn(Optional.of(usuarioDTO));

        ResponseEntity<String> response = usuarioController.save(usuarioDTO);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Nome de usuário já existe!", response.getBody());
        verify(service, times(1)).findByUsername(anyString());
        verify(service, never()).save(any(UsuarioDTO.class));
    }

    @Test
    void shouldReturnUserWhenUserExists() {
        when(service.findOne(anyLong())).thenReturn(Optional.of(usuarioDTO));

        Optional<UsuarioDTO> response = usuarioController.findById(1L);

        assertTrue(response.isPresent());
        assertEquals(usuarioDTO.getUsername(), response.get().getUsername());
        verify(service, times(1)).findOne(anyLong());
    }

    @Test
    void shouldReturnEmptyWhenUserDoesNotExist() {
        when(service.findOne(anyLong())).thenReturn(Optional.empty());

        Optional<UsuarioDTO> response = usuarioController.findById(1L);

        assertTrue(response.isEmpty());
        verify(service, times(1)).findOne(anyLong());
    }

    @Test
    void shouldReturnPageOfUsers() {
        Pageable pageable = PageRequest.of(0, 10);
        List<UsuarioDTO> usuarioList = Arrays.asList(usuarioDTO, new UsuarioDTO());
        Page<UsuarioDTO> usuarioPage = new PageImpl<>(usuarioList, pageable, usuarioList.size());

        when(service.findAll(any(Pageable.class))).thenReturn(usuarioPage);

        ResponseEntity<Page<UsuarioDTO>> response = usuarioController.findAll(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioPage, response.getBody());
        verify(service, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void shouldReturnNoContentWhenUserIsDeleted() {
        doNothing().when(service).delete(anyLong());

        ResponseEntity<Void> response = usuarioController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).delete(anyLong());
    }

    @Test
    void shouldReturnOkWhenUserIsUpdated() {
        when(service.update(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioController.update(usuarioDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTO, response.getBody());
        verify(service, times(1)).update(any(UsuarioDTO.class));
    }
}