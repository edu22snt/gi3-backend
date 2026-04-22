package com.br.gi3.repository;

import com.br.gi3.model.Usuario;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);

    @Query("SELECT t FROM Usuario t WHERE " +
            "LOWER(t.username) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Usuario> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
