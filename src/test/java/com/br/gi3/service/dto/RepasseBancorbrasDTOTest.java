package com.br.gi3.service.dto;

import com.br.gi3.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RepasseBancorbrasDTOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RepasseBancorbrasDTO.class);

        RepasseBancorbrasDTO repasseBancorbras1 = new RepasseBancorbrasDTO();
        RepasseBancorbrasDTO repasseBancorbras2 = new RepasseBancorbrasDTO();

        repasseBancorbras1.setId(1L);
        repasseBancorbras2.setId(repasseBancorbras1.getId());

        repasseBancorbras1.setCliente("Dennis");
        repasseBancorbras1.setContrato("1111988");
        repasseBancorbras1.setVenda("11/22/2022");
        repasseBancorbras1.setMes("15/12/2023");
        repasseBancorbras1.setBem("IM400");
        repasseBancorbras1.setParcela("1");
        repasseBancorbras1.setValorBase("R$400.000,00");
        repasseBancorbras1.setComissaoGi3("0");
        repasseBancorbras1.setComissaoVendedor("R$2.080,00");
        repasseBancorbras1.setDescontoComissao("R$124,00");
        repasseBancorbras1.setComissaoLiquida("R$1.955,20");
        repasseBancorbras1.setPg("OK");

        assertThat(repasseBancorbras1).isEqualTo(repasseBancorbras2);

        repasseBancorbras2.setId(2L);

        assertThat(repasseBancorbras1).isNotEqualTo(repasseBancorbras2);
    }

}