package com.br.gi3.service.mapper;

import com.br.gi3.model.Vendedor;
import com.br.gi3.service.dto.VendedorDTO;
import org.springframework.stereotype.Component;

@Component
public class VendedorMapper {

    public static Vendedor toEntity(VendedorDTO dto) {
        if (dto == null) {
            return null;
        }

        Vendedor entity = new Vendedor();

        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setStatus(dto.getStatus());

        return entity;
    }

    public static VendedorDTO toDto(Vendedor entity) {
        if (entity == null) {
            return null;
        }

        VendedorDTO dto = new VendedorDTO();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}