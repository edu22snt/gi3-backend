package com.br.gi3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_CONTRATO_PARCELA")
public class ContratoParcela implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_CONTRATO_PARCELA")
    private Long id;

    @Column(name = "NR_PARCELA", nullable = false)
    private String numeroParcela;

    @Column(name = "NM_STATUS")
    private String status;

    @Column(name = "NU_DESCONTO_COMISSAO", precision = 15, scale = 2)
    private BigDecimal descontoComissao;

    @Column(name = "NU_BASE", precision = 15, scale = 2)
    private BigDecimal base;

    @Column(name = "NU_COMISSAO", precision = 15, scale = 2)
    private BigDecimal comissao;

    @Column(name = "NU_LIQUIDO", precision = 15, scale = 2)
    private BigDecimal liquido;

    @ManyToOne
    @JoinColumn(name = "CD_CONTRATO", nullable = false)
    @JsonBackReference
    private Contrato contrato;

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

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
