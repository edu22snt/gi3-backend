package com.br.gi3.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_PESTACAO_SERVICO")
public class PrestacaoServico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CD_PRESTACAO_SERVICO")
    private Long id;

    @Column(name="NM_VENDEDOR")
    private String vendedor;

    @Column(name="NM_CONTRATO")
    private String contrato;

    @Column(name="NM_PARCELA")
    private String parcela;

    @Column(name="NM_VALOR", precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name="NM_EMPRESA")
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
        if (!(o instanceof PrestacaoServico)) {
            return false;
        }
        return id != null && id.equals(((PrestacaoServico) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "PrestacaoServico{" +
                "id=" + getId() +
                ", vendedor='" + getVendedor() + "'" +
                ", contrato='" + getContrato() + "'" +
                ", parcela='" + getParcela() + "'" +
                ", valor='" + getValor() + "'" +
                ", empresa='" + getEmpresa() + "'" +
                "}";
    }

}
