package com.br.gi3.service.mapper;

import com.br.gi3.model.RepasseBancorbras;
import com.br.gi3.service.dto.RepasseBancorbrasDTO;
import org.springframework.stereotype.Component;


@Component
public class RepasseBancorbrasMapper {

    public static RepasseBancorbras toEntity(RepasseBancorbrasDTO repasseBancorbrasDTO) {
        RepasseBancorbras repasseBancorbras = new RepasseBancorbras();

        repasseBancorbras.setId(repasseBancorbrasDTO.getId());
        repasseBancorbras.setCliente(repasseBancorbrasDTO.getCliente());
        repasseBancorbras.setContrato(repasseBancorbrasDTO.getContrato());
        repasseBancorbras.setVenda(repasseBancorbrasDTO.getVenda());
        repasseBancorbras.setMes(repasseBancorbrasDTO.getMes());
        repasseBancorbras.setBem(repasseBancorbrasDTO.getBem());
        repasseBancorbras.setParcela(repasseBancorbrasDTO.getParcela());
        repasseBancorbras.setValorBase(repasseBancorbrasDTO.getValorBase());
        repasseBancorbras.setComissaoGi3(repasseBancorbrasDTO.getComissaoGi3());
        repasseBancorbras.setComissaoVendedor(repasseBancorbrasDTO.getComissaoVendedor());
        repasseBancorbras.setDescontoComissao(repasseBancorbrasDTO.getDescontoComissao());
        repasseBancorbras.setComissaoLiquida(repasseBancorbrasDTO.getComissaoLiquida());
        repasseBancorbras.setPg(repasseBancorbrasDTO.getPg());

        return repasseBancorbras;
    }

    public static RepasseBancorbrasDTO toDto(RepasseBancorbras repasseBancorbras) {
        RepasseBancorbrasDTO repasseBancorbrasDTO = new RepasseBancorbrasDTO();

        repasseBancorbrasDTO.setId(repasseBancorbras.getId());
        repasseBancorbrasDTO.setCliente(repasseBancorbras.getCliente());
        repasseBancorbrasDTO.setContrato(repasseBancorbras.getContrato());
        repasseBancorbrasDTO.setVenda(repasseBancorbras.getVenda());
        repasseBancorbrasDTO.setMes(repasseBancorbras.getMes());
        repasseBancorbrasDTO.setBem(repasseBancorbras.getBem());
        repasseBancorbrasDTO.setParcela(repasseBancorbras.getParcela());
        repasseBancorbrasDTO.setValorBase(repasseBancorbras.getValorBase());
        repasseBancorbrasDTO.setComissaoGi3(repasseBancorbras.getComissaoGi3());
        repasseBancorbrasDTO.setComissaoVendedor(repasseBancorbras.getComissaoVendedor());
        repasseBancorbrasDTO.setDescontoComissao(repasseBancorbras.getDescontoComissao());
        repasseBancorbrasDTO.setComissaoLiquida(repasseBancorbras.getComissaoLiquida());
        repasseBancorbrasDTO.setPg(repasseBancorbras.getPg());

        return repasseBancorbrasDTO;
    }

}