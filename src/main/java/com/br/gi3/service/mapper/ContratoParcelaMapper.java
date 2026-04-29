package com.br.gi3.service.mapper;

import com.br.gi3.model.ContratoParcela;
import com.br.gi3.service.dto.ContratoParcelaDTO;
import org.springframework.stereotype.Component;

@Component
public class ContratoParcelaMapper {

    public static ContratoParcela toEntity(ContratoParcelaDTO dto) {
        if (dto == null) return null;

        ContratoParcela entity = new ContratoParcela();
        entity.setId(dto.getId());
        entity.setNumeroParcela(dto.getNumeroParcela());
        entity.setStatus(dto.getStatus());
        entity.setPorcentagemComissao(dto.getPorcentagemComissao());
        entity.setBase(dto.getBase());
        entity.setLiquido(dto.getLiquido());
        entity.setComissao(dto.getComissao());
        return entity;
    }

    public static ContratoParcelaDTO toDto(ContratoParcela entity) {
        if (entity == null) return null;

        ContratoParcelaDTO dto = new ContratoParcelaDTO();
        dto.setId(entity.getId());
        dto.setNumeroParcela(entity.getNumeroParcela());
        dto.setStatus(entity.getStatus());
        dto.setPorcentagemComissao(entity.getPorcentagemComissao());
        dto.setBase(entity.getBase());
        dto.setLiquido(entity.getLiquido());
        dto.setComissao(entity.getComissao());
        return dto;
    }
}
