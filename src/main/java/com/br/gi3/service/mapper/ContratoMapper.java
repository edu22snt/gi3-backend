package com.br.gi3.service.mapper;

import com.br.gi3.model.Contrato;
import com.br.gi3.model.ContratoParcela;
import com.br.gi3.service.dto.ContratoDTO;
import com.br.gi3.service.dto.ContratoParcelaDTO;
import org.springframework.stereotype.Component;

@Component
public class ContratoMapper {

    public static Contrato toEntity(ContratoDTO dto) {
        if (dto == null) return null;

        Contrato entity = new Contrato();
        entity.setId(dto.getId());
        entity.setNumeroContrato(dto.getNumeroContrato());
        entity.setVendedor(dto.getVendedor());
        entity.setTipo(dto.getTipo());
        entity.setEmpresa(dto.getEmpresa());
        entity.setValor(dto.getValor());

        entity.clearParcelas();

        if (dto.getParcelas() != null) {
            dto.getParcelas().forEach(p -> {
                ContratoParcela parcela = new ContratoParcela();
                parcela.setId(p.getId());
                parcela.setNumeroParcela(p.getNumeroParcela());
                parcela.setStatus(p.getStatus());
                entity.addParcela(parcela);
            });
        }
        return entity;
    }

    public static ContratoDTO toDto(Contrato entity) {
        if (entity == null) return null;

        ContratoDTO dto = new ContratoDTO();
        dto.setId(entity.getId());
        dto.setNumeroContrato(entity.getNumeroContrato());
        dto.setVendedor(entity.getVendedor());
        dto.setTipo(entity.getTipo());
        dto.setEmpresa(entity.getEmpresa());
        dto.setValor(entity.getValor());

        if (entity.getParcelas() != null) {
            dto.setParcelas(
                entity.getParcelas().stream().map(p -> {
                    ContratoParcelaDTO parcelaDTO = new ContratoParcelaDTO();
                    parcelaDTO.setId(p.getId());
                    parcelaDTO.setNumeroParcela(p.getNumeroParcela());
                    parcelaDTO.setStatus(p.getStatus());
                    return parcelaDTO;
                }).toList()
            );
        }
        return dto;
    }
}
