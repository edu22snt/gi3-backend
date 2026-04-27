package com.br.gi3.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CONTRATO")
public class Contrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_CONTRATO")
    private Long id;

    @Column(name = "NR_CONTRATO", nullable = false)
    private String numeroContrato;

    @Column(name = "NM_VENDEDOR")
    private String vendedor;

    @Column(name = "DS_TIPO")
    private String tipo;

    @Column(name = "NM_EMPRESA")
    private String empresa;

    @Column(name = "NU_QNT_PARCELAS")
    private int qntParcelas;

    @Column(name = "VL_VALOR", precision = 15, scale = 2)
    private BigDecimal valor;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ContratoParcela> parcelas = new ArrayList<>();

    public void addParcela(ContratoParcela parcela) {
        parcela.setContrato(this);
        this.parcelas.add(parcela);
    }

    public void clearParcelas() {
        for (ContratoParcela p : parcelas) {
            p.setContrato(null);
        }
        parcelas.clear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getQntParcelas() {
        return qntParcelas;
    }

    public void setQntParcelas(int qntParcelas) {
        this.qntParcelas = qntParcelas;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public List<ContratoParcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<ContratoParcela> parcelas) {
        this.parcelas = parcelas;
    }
}
