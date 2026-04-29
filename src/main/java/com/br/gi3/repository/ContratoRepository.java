package com.br.gi3.repository;

import com.br.gi3.model.Contrato;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    Optional<Contrato> findByNumeroContrato(String numeroContrato);

    @Query("SELECT c FROM Contrato c WHERE LOWER(c.vendedor.nome) LIKE LOWER(CONCAT('%', :param, '%')) " +
            "OR LOWER(c.empresa) LIKE LOWER(CONCAT('%', :param, '%')) " +
            "OR LOWER(c.numeroContrato) LIKE LOWER(CONCAT('%', :param, '%'))")
    Page<Contrato> searchByKeyword(@Param("param") String param, Pageable pageable);
}
