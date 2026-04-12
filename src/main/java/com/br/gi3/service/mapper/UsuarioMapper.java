package com.br.gi3.service.mapper;

import com.br.gi3.model.PrestacaoServico;
import com.br.gi3.model.Usuario;
import com.br.gi3.service.dto.PrestacaoServicoDTO;
import com.br.gi3.service.dto.UsuarioDTO;
import org.springframework.stereotype.Component;


@Component
public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setId(usuarioDTO.getId());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setType(usuarioDTO.getType());

        return usuario;
    }

    public static UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setType(usuario.getType());

        return usuarioDTO;
    }

}