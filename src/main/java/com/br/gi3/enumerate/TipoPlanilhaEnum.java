package com.br.gi3.enumerate;

public enum TipoPlanilhaEnum {

    BANCORBRAS(1),
    HS(2),
    PESTACAO_SERVICO_BANCORBRAS(3),
    PESTACAO_SERVICO_HS(4);

    private final int tipo;

    TipoPlanilhaEnum(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

}
