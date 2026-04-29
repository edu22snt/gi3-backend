package com.br.gi3.service.dto;

import java.math.BigDecimal;

public class RepasseBancorbrasDTO {

    private Long id;
    private String cliente;
    private String contrato;
    private String venda;
    private String mes;
    private String bem;
    private String parcela;
    private BigDecimal valorBase;
    private BigDecimal comissaoGi3;
    private BigDecimal comissaoVendedor;
    private BigDecimal descontoComissao;
    private BigDecimal comissaoLiquida;
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

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public void setValorBase(BigDecimal valorBase) {
        this.valorBase = valorBase;
    }

    public BigDecimal getComissaoGi3() {
        return comissaoGi3;
    }

    public void setComissaoGi3(BigDecimal comissaoGi3) {
        this.comissaoGi3 = comissaoGi3;
    }

    public BigDecimal getComissaoVendedor() {
        return comissaoVendedor;
    }

    public void setComissaoVendedor(BigDecimal comissaoVendedor) {
        this.comissaoVendedor = comissaoVendedor;
    }

    public BigDecimal getDescontoComissao() {
        return descontoComissao;
    }

    public void setDescontoComissao(BigDecimal descontoComissao) {
        this.descontoComissao = descontoComissao;
    }

    public BigDecimal getComissaoLiquida() {
        return comissaoLiquida;
    }

    public void setComissaoLiquida(BigDecimal comissaoLiquida) {
        this.comissaoLiquida = comissaoLiquida;
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
        if (!(o instanceof RepasseBancorbrasDTO)) {
            return false;
        }
        return id != null && id.equals(((RepasseBancorbrasDTO) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "RepasseBancorbrasDTO{" +
                "id=" + getId() +
                ", cliente='" + getCliente() + "'" +
                ", contrato='" + getContrato() + "'" +
                ", venda='" + getVenda() + "'" +
                ", mes='" + getMes() + "'" +
                ", bem='" + getBem() + "'" +
                ", parcela='" + getParcela() + "'" +
                ", valorBase='" + getValorBase() + "'" +
                ", comissaoGi3='" + getComissaoGi3() + "'" +
                ", comissaoVendedor='" + getComissaoVendedor() + "'" +
                ", descontoComissao='" + getDescontoComissao() + "'" +
                ", comissaoLiquida='" + getComissaoLiquida() + "'" +
                ", pg='" + getPg() + "'" +
                "}";
    }
}
