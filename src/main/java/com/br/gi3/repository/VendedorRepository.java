package com.br.gi3.repository;

import com.br.gi3.model.Vendedor;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    Optional<Vendedor> findByNome(String nome);

    @Query("""
        SELECT v FROM Vendedor v
        WHERE LOWER(v.nome) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    Page<Vendedor> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
