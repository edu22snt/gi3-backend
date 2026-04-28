package com.br.gi3.service.dto;

import java.math.BigDecimal;

public class ContratoParcelaDTO {

    private Long id;
    private String numeroParcela;
    private String status;
    private BigDecimal porcentagemComissao;
    private BigDecimal base;
    private BigDecimal comissao;
    private BigDecimal liquido;
    private ContratoDTO contratoDTO;

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

    public BigDecimal getPorcentagemComissao() {
        return porcentagemComissao;
    }

    public void setPorcentagemComissao(BigDecimal porcentagemComissao) {
        this.porcentagemComissao = porcentagemComissao;
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

    public ContratoDTO getContratoDTO() {
        return contratoDTO;
    }

    public void setContratoDTO(ContratoDTO contratoDTO) {
        this.contratoDTO = contratoDTO;
    }
}
