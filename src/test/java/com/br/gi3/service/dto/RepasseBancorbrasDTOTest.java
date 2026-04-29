package com.br.gi3.service.dto;

import com.br.gi3.TestUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
        repasseBancorbras1.setValorBase(converter("R$400.000,00"));
        repasseBancorbras1.setComissaoGi3(converter("0"));
        repasseBancorbras1.setComissaoVendedor(converter("R$2.080,00"));
        repasseBancorbras1.setDescontoComissao(converter("R$124,00"));
        repasseBancorbras1.setComissaoLiquida(converter("R$1.955,20"));
        repasseBancorbras1.setPg("OK");

        assertThat(repasseBancorbras1).isEqualTo(repasseBancorbras2);

        repasseBancorbras2.setId(2L);

        assertThat(repasseBancorbras1).isNotEqualTo(repasseBancorbras2);
    }

    private BigDecimal converter(String valor) {
        String valorLimpo = valor
                .replace("R$", "")
                .replace(".", "")
                .replace(",", ".")
                .trim();

        return new BigDecimal(valorLimpo);
    }

}