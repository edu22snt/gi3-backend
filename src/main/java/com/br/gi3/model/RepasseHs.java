package com.br.gi3.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_REPASSE_HS")
public class RepasseHs implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_REPASSE_HS")
    private Long id;

    @Column(name = "NM_CLIENTE")
    private String cliente;

    @Column(name = "NM_CONTRATO")
    private String contrato;

    @Column(name = "NM_VENDA")
    private String venda;

    @Column(name = "NM_MES")
    private String mes;

    @Column(name = "NM_BEM")
    private String bem;

    @Column(name = "NM_PARCELA")
    private String parcela;

    @Column(name = "NM_VALOR_BASE_COM")
    private String valorBase;

    @Column(name = "NM_COMISSAO_GI3")
    private String comissao_gi3;

    @Column(name = "NM_COMISSAO_VEND")
    private String comissao_vendedor;

    @Column(name = "NM_PAGAMENTO")
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

    public String getComissao_gi3() {
        return comissao_gi3;
    }

    public void setComissao_gi3(String comissao_gi3) {
        this.comissao_gi3 = comissao_gi3;
    }

    public String getComissao_vendedor() {
        return comissao_vendedor;
    }

    public void setComissao_vendedor(String comissao_vendedor) {
        this.comissao_vendedor = comissao_vendedor;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }
}
