package com.br.gi3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;

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

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
