package com.br.gi3.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_ROLE")
public class Role implements Serializable {

    @Id
    @Column(name="NM_ROLE", unique = true, nullable = false)
    private String nome;

    public Role() {
    }

    public Role(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
//        return nome != null && nome.equals(((Role) o).nome);
        return Objects.equals(nome, role.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Role{" +
                "nome=" + getNome() +
                "}";
    }
}
