package com.br.gi3.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrestacaoServicoDTO implements Serializable {

    private Long id;
    private String vendedor;
    private String contrato;
    private String parcela;
    private BigDecimal valor;
    private String empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrestacaoServicoDTO)) {
            return false;
        }
        return id != null && id.equals(((PrestacaoServicoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "PrestacaoServicoDTO{" +
                "id=" + getId() +
                ", vendedor='" + getVendedor() + "'" +
                ", contrato='" + getContrato() + "'" +
                ", parcela='" + getParcela() + "'" +
                ", valor='" + getValor() + "'" +
                ", empresa='" + getEmpresa() + "'" +
                "}";
    }
}
