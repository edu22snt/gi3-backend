package com.br.gi3.service.mapper;

import com.br.gi3.model.PrestacaoServico;
import com.br.gi3.service.dto.PrestacaoServicoDTO;
import org.springframework.stereotype.Component;


@Component
public class PrestacaoServicoMapper {

    public static PrestacaoServico toEntity(PrestacaoServicoDTO prestacaoServicoDTO) {
        PrestacaoServico prestacaoServico = new PrestacaoServico();

        prestacaoServico.setId(prestacaoServicoDTO.getId());
        prestacaoServico.setVendedor(prestacaoServicoDTO.getVendedor());
        prestacaoServico.setContrato(prestacaoServicoDTO.getContrato());
        prestacaoServico.setParcela(prestacaoServicoDTO.getParcela());
        prestacaoServico.setValor(prestacaoServicoDTO.getValor());
        prestacaoServico.setEmpresa(prestacaoServicoDTO.getEmpresa());

        return prestacaoServico;
    }

    public static PrestacaoServicoDTO toDto(PrestacaoServico prestacaoServico) {
        PrestacaoServicoDTO prestacaoServicoDTO = new PrestacaoServicoDTO();

        prestacaoServicoDTO.setId(prestacaoServico.getId());
        prestacaoServicoDTO.setVendedor(prestacaoServico.getVendedor());
        prestacaoServicoDTO.setContrato(prestacaoServico.getContrato());
        prestacaoServicoDTO.setParcela(prestacaoServico.getParcela());
        prestacaoServicoDTO.setValor(prestacaoServico.getValor());
        prestacaoServicoDTO.setEmpresa(prestacaoServico.getEmpresa());

        return prestacaoServicoDTO;
    }

}