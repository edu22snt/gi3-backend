package com.br.gi3.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_REPASSE_BANCORBRAS")
public class RepasseBancorbras implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_REPASSE_BANCORBRAS")
    private Long id;

    @Column(name="NM_CLIENTE")
    private String cliente;

    @Column(name="NM_CONTRATO")
    private String contrato;

    @Column(name="NM_VENDA")
    private String venda;

    @Column(name="NM_MES")
    private String mes;

    @Column(name="NM_BEM")
    private String bem;

    @Column(name="NM_PARCELA")
    private String parcela;

    @Column(name="NM_VALOR_BASE", precision = 15, scale = 2)
    private BigDecimal valorBase;

    @Column(name="NM_COMISSAO_GI3", precision = 15, scale = 2)
    private BigDecimal comissaoGi3;

    @Column(name="NM_COMISSAO_VENDEDOR", precision = 15, scale = 2)
    private BigDecimal comissaoVendedor;

    @Column(name="NM_DESCONTO_COMISSAO", precision = 15, scale = 2)
    private BigDecimal descontoComissao;

    @Column(name="NM_COMISSAO_LIQUIDA", precision = 15, scale = 2)
    private BigDecimal comissaoLiquida;

    @Column(name="NM_PAGAMENTO")
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
        if (!(o instanceof RepasseBancorbras)) {
            return false;
        }
        return id != null && id.equals(((RepasseBancorbras) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "RepasseBancorbras{" +
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
