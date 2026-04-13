package com.br.gi3.service.dto;

import com.br.gi3.model.Role;
import com.br.gi3.model.UsuarioRole;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDTO implements Serializable {

    private Long id;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
