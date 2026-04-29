package com.br.gi3.service.dto;

import java.math.BigDecimal;

public class ContratoParcelaDTO {

    private Long id;
    private String numeroParcela;
    private String status;
    private BigDecimal descontoComissao;
    private BigDecimal base;
    private BigDecimal comissao;
    private BigDecimal liquido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getDescontoComissao() {
        return descontoComissao;
    }

    public void setDescontoComissao(BigDecimal descontoComissao) {
        this.descontoComissao = descontoComissao;
    }

    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public BigDecimal getLiquido() {
        return liquido;
    }

    public void setLiquido(BigDecimal liquido) {
        this.liquido = liquido;
    }

}
