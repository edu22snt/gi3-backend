package com.br.gi3.model;

import com.br.gi3.TestUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RepasseHsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RepasseHs.class);

        RepasseHs repasseHs1 = new RepasseHs();
        RepasseHs repasseHs2 = new RepasseHs();

        repasseHs1.setId(1L);
        repasseHs2.setId(repasseHs1.getId());

        repasseHs1.setCliente("Dennis");
        repasseHs1.setContrato("1111988");
        repasseHs1.setVenda("11/22/2022");
        repasseHs1.setMes("15/12/2023");
        repasseHs1.setBem("IM400");
        repasseHs1.setParcela("1");
        repasseHs1.setValorBase(converter("R$400.000,00"));
        repasseHs1.setComissao_gi3(converter("0"));
        repasseHs1.setComissao_vendedor(converter("R$2.080,00"));
        repasseHs1.setPg("OK");

        assertThat(repasseHs1).isEqualTo(repasseHs2);

        repasseHs2.setId(2L);

        assertThat(repasseHs1).isNotEqualTo(repasseHs2);
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