package com.br.gi3.service.dto;

public class ContratoParcelaDTO {

    private Long id;
    private String numeroParcela;
    private String status;
    private ContratoDTO contratoDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ContratoDTO getContratoDTO() {
        return contratoDTO;
    }

    public void setContratoDTO(ContratoDTO contratoDTO) {
        this.contratoDTO = contratoDTO;
    }
}
