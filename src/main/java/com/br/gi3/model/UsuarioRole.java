package com.br.gi3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_USUARIO_ROLES")
public class UsuarioRole {

    @Id
    @Column(name="CD_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NM_ROLES")
    private String nome;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UsuarioRole)) {
            return false;
        }
        return id != null && id.equals(((UsuarioRole) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "UsuarioRole{" +
                "id=" + getId() +
                ", nome='" + getNome() + "'" +
                "}";
    }
}
