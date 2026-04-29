package com.br.gi3.service.dto;

import java.io.Serializable;

public class VendedorDTO implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendedorDTO)) {
            return false;
        }
        return id != null && id.equals(((VendedorDTO) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "VendedorDTO{" +
                "id=" + getId() +
                ", nome='" + getNome() + "'" +
                ", email='" + getEmail() + "'" +
                ", telefone='" + getTelefone() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
