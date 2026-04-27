package com.br.gi3.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ContratoDTO implements Serializable {

    private Long id;
    private String numeroContrato;
    private String vendedor;
    private String tipo;
    private String empresa;
    private int qntParcelas;
    private BigDecimal valor;
    private List<ContratoParcelaDTO> parcelas;

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

    public List<ContratoParcelaDTO> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<ContratoParcelaDTO> parcelas) {
        this.parcelas = parcelas;
    }
}
