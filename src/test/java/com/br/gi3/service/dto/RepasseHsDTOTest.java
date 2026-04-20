package com.br.gi3.service.dto;

import com.br.gi3.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RepasseHsDTOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RepasseHsDTO.class);

        RepasseHsDTO repasseHs1 = new RepasseHsDTO();
        RepasseHsDTO repasseHs2 = new RepasseHsDTO();

        repasseHs1.setId(1L);
        repasseHs2.setId(repasseHs1.getId());

        repasseHs1.setCliente("Dennis");
        repasseHs1.setContrato("1111988");
        repasseHs1.setVenda("11/22/2022");
        repasseHs1.setMes("15/12/2023");
        repasseHs1.setBem("IM400");
        repasseHs1.setParcela("1");
        repasseHs1.setValorBase("R$400.000,00");
        repasseHs1.setComissaoGi3("0");
        repasseHs1.setComissaoVendedor("R$2.080,00");
        repasseHs1.setPg("OK");

        assertThat(repasseHs1).isEqualTo(repasseHs2);

        repasseHs2.setId(2L);

        assertThat(repasseHs1).isNotEqualTo(repasseHs2);
    }

}