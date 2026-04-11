package com.br.gi3.enumerate;

public enum TipoPlanilhaEnum {

    BANCORBRAS(1),
    HS(2),
    PESTACAO_SERVICO(3);

    private final int tipo;

    TipoPlanilhaEnum(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

}
