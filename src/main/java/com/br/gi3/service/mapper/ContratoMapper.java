package com.br.gi3.service.mapper;

import com.br.gi3.model.Contrato;
import com.br.gi3.model.ContratoParcela;
import com.br.gi3.model.Vendedor;
import com.br.gi3.service.dto.ContratoDTO;
import com.br.gi3.service.dto.ContratoParcelaDTO;
import com.br.gi3.service.dto.VendedorDTO;
import org.springframework.stereotype.Component;

@Component
public class ContratoMapper {

    public static Contrato toEntity(ContratoDTO dto) {
        if (dto == null) return null;

        Contrato entity = new Contrato();
        Vendedor vendedor = new Vendedor();
        entity.setId(dto.getId());
        entity.setNumeroContrato(dto.getNumeroContrato());
        entity.setTipo(dto.getTipo());
        entity.setEmpresa(dto.getEmpresa());
        entity.setQntParcelas(dto.getQntParcelas());
        entity.setValor(dto.getValor());

        vendedor.setId(dto.getVendedor().getId());
        vendedor.setNome(dto.getVendedor().getNome());
        vendedor.setEmail(dto.getVendedor().getEmail());
        vendedor.setTelefone(dto.getVendedor().getTelefone());
        vendedor.setStatus(dto.getVendedor().getStatus());

        entity.setVendedor(vendedor);

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
        VendedorDTO vendedorDTO = new VendedorDTO();
        dto.setId(entity.getId());
        dto.setNumeroContrato(entity.getNumeroContrato());
        dto.setTipo(entity.getTipo());
        dto.setEmpresa(entity.getEmpresa());
        dto.setQntParcelas(entity.getQntParcelas());
        dto.setValor(entity.getValor());

        vendedorDTO.setId(entity.getVendedor().getId());
        vendedorDTO.setNome(entity.getVendedor().getNome());
        vendedorDTO.setEmail(entity.getVendedor().getEmail());
        vendedorDTO.setTelefone(entity.getVendedor().getTelefone());
        vendedorDTO.setStatus(entity.getVendedor().getStatus());

        dto.setVendedor(vendedorDTO);
        if (entity.getParcelas() != null) {
            dto.setParcelas(
                entity.getParcelas().stream().map(p -> {
                    ContratoParcelaDTO parcelaDTO = new ContratoParcelaDTO();
                    parcelaDTO.setId(p.getId());
                    parcelaDTO.setNumeroParcela(p.getNumeroParcela());
                    parcelaDTO.setStatus(p.getStatus());
                    parcelaDTO.setDescontoComissao(p.getDescontoComissao());
                    parcelaDTO.setBase(p.getBase());
                    parcelaDTO.setComissao(p.getComissao());
                    parcelaDTO.setLiquido(p.getLiquido());
                    return parcelaDTO;
                }).toList()
            );
        }
        return dto;
    }
}
