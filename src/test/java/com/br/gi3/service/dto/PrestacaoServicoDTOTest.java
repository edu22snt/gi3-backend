package com.br.gi3.service.dto;

import com.br.gi3.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PrestacaoServicoDTOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrestacaoServicoDTO.class);

        PrestacaoServicoDTO prestacaoServico1 = new PrestacaoServicoDTO();
        PrestacaoServicoDTO prestacaoServico2 = new PrestacaoServicoDTO();

        prestacaoServico1.setId(1L);
        prestacaoServico2.setId(prestacaoServico1.getId());

        prestacaoServico1.setEmpresa("bancorbras");
        prestacaoServico1.setParcela("1");
        prestacaoServico1.setContrato("XXXCCC");
        prestacaoServico1.setVendedor("Rodrigo");
        prestacaoServico1.setValor("R$100");

        assertThat(prestacaoServico1).isEqualTo(prestacaoServico2);

        prestacaoServico2.setId(2L);
        assertThat(prestacaoServico1).isNotEqualTo(prestacaoServico2);
    }

}