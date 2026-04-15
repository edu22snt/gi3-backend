package com.br.gi3.repository;

import com.br.gi3.model.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, Long> {
}
