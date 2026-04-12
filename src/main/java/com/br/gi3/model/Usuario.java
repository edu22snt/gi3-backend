package com.br.gi3.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CD_USUARIO")
    private Long id;

    @Column(name="NM_NOME")
    private String username;

    @Column(name="NM_SENHA")
    private String password;

    @Column(name="NM_TIPO")
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
