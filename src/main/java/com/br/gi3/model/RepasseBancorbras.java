package com.br.gi3.model;

import jakarta.persistence.*;

import java.io.Serializable;

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

    @Column(name="NM_VALOR_BASE")
    private String valorBase;

    @Column(name="NM_COMISSAO_GI3")
    private String comissaoGi3;

    @Column(name="NM_COMISSAO_VENDEDOR")
    private String comissaoVendedor;

    @Column(name="NM_DESCONTO_COMISSAO")
    private String descontoComissao;

    @Column(name="NM_COMISSAO_LIQUIDA")
    private String comissaoLiquida;

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

    public String getDescontoComissao() {
        return descontoComissao;
    }

    public void setDescontoComissao(String descontoComissao) {
        this.descontoComissao = descontoComissao;
    }

    public String getComissaoLiquida() {
        return comissaoLiquida;
    }

    public void setComissaoLiquida(String comissaoLiquida) {
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
