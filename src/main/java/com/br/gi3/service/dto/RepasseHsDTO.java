package com.br.gi3.service.dto;

import java.io.Serializable;

public class RepasseHsDTO implements Serializable {

    private Long id;
    private String cliente;
    private String contrato;
    private String venda;
    private String mes;
    private String bem;
    private String parcela;
    private String valorBase;
    private String comissaoGi3;
    private String comissaoVendedor;
    private String pg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getVenda() {
        return venda;
    }

    public void setVenda(String venda) {
        this.venda = venda;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getBem() {
        return bem;
    }

    public void setBem(String bem) {
        this.bem = bem;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getValorBase() {
        return valorBase;
    }

    public void setValorBase(String valorBase) {
        this.valorBase = valorBase;
    }

    public String getComissaoGi3() {
        return comissaoGi3;
    }

    public void setComissaoGi3(String comissaoGi3) {
        this.comissaoGi3 = comissaoGi3;
    }

    public String getComissaoVendedor() {
        return comissaoVendedor;
    }

    public void setComissaoVendedor(String comissaoVendedor) {
        this.comissaoVendedor = comissaoVendedor;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RepasseHsDTO)) {
            return false;
        }
        return id != null && id.equals(((RepasseHsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "RepasseHsDTO{" +
                "id=" + getId() +
                ", cliente='" + getCliente() + "'" +
                ", contrato='" + getContrato() + "'" +
                ", venda='" + getVenda() + "'" +
                ", mes='" + getMes() + "'" +
                ", bem='" + getBem() + "'" +
                ", parcela='" + getParcela() + "'" +
                ", valorBase='" + getValorBase() + "'" +
                ", comissao_gi3='" + getComissaoGi3() + "'" +
                ", comissao_vendedor='" + getComissaoVendedor() + "'" +
                ", pg='" + getPg() + "'" +
                "}";
    }
}
