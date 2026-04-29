package com.br.gi3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_VENDEDOR")
public class Vendedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_VENDEDOR")
    private Long id;

    @Column(name = "NM_VENDEDOR", nullable = false)
    private String nome;

    @Column(name = "DS_EMAIL")
    private String email;

    @Column(name = "NR_TELEFONE")
    private String telefone;

    @Column(name = "NM_STATUS")
    private String status;

    @OneToMany(mappedBy = "vendedor")
    @JsonIgnore
    private List<Contrato> contratos = new ArrayList<>();

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

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vendedor)) {
            return false;
        }
        return id != null && id.equals(((Vendedor) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "id=" + getId() +
                ", nome='" + getNome() + "'" +
                ", email='" + getEmail() + "'" +
                ", telefone='" + getTelefone() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
