package com.br.gi3.service.mapper;

import com.br.gi3.model.RepasseHs;
import com.br.gi3.service.dto.RepasseHsDTO;
import org.springframework.stereotype.Component;


@Component
public class RepasseHsMapper {

    public static RepasseHs toEntity(RepasseHsDTO repasseHsDTO) {
        RepasseHs repasseHs = new RepasseHs();

        repasseHs.setId(repasseHsDTO.getId());
        repasseHs.setCliente(repasseHsDTO.getCliente());
        repasseHs.setContrato(repasseHsDTO.getContrato());
        repasseHs.setVenda(repasseHsDTO.getVenda());
        repasseHs.setMes(repasseHsDTO.getMes());
        repasseHs.setBem(repasseHsDTO.getBem());
        repasseHs.setParcela(repasseHsDTO.getParcela());
        repasseHs.setValorBase(repasseHsDTO.getValorBase());
        repasseHs.setComissao_gi3(repasseHsDTO.getComissaoGi3());
        repasseHs.setComissao_vendedor(repasseHsDTO.getComissaoVendedor());
        repasseHs.setPg(repasseHsDTO.getPg());

        return repasseHs;
    }

    public static RepasseHsDTO toDto(RepasseHs repasseHs) {
        RepasseHsDTO repasseHsDTO = new RepasseHsDTO();

        repasseHsDTO.setId(repasseHs.getId());
        repasseHsDTO.setCliente(repasseHs.getCliente());
        repasseHsDTO.setContrato(repasseHs.getContrato());
        repasseHsDTO.setVenda(repasseHs.getVenda());
        repasseHsDTO.setMes(repasseHs.getMes());
        repasseHsDTO.setBem(repasseHs.getBem());
        repasseHsDTO.setParcela(repasseHs.getParcela());
        repasseHsDTO.setValorBase(repasseHs.getValorBase());
        repasseHsDTO.setComissaoGi3(repasseHs.getComissao_gi3());
        repasseHsDTO.setComissaoVendedor(repasseHs.getComissao_vendedor());
        repasseHsDTO.setPg(repasseHs.getPg());

        return repasseHsDTO;
    }

}