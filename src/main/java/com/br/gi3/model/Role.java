package com.br.gi3.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_ROLE")
public class Role implements Serializable {

    @Id
    @Column(name="NM_ROLE", unique = true, nullable = false)
    private String nome; // O nome da role é a chave primária

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return nome.equals(role.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
