package com.br.gi3.repository;

import com.br.gi3.model.ContratoParcela;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContratoParcelaRepository extends JpaRepository<ContratoParcela, Long> {

    @Query("SELECT c FROM ContratoParcela c WHERE c.contrato.numeroContrato = :numeroContrato " +
            "AND c.numeroParcela = :numeroParcela AND c.status = 'PENDENTE'")
    Optional<ContratoParcela> findByNumeroContrato(@Param("numeroContrato") String numeroContrato, @Param("numeroParcela") String numeroParcela);

    @Query("SELECT c FROM ContratoParcela c WHERE LOWER(c.contrato.numeroContrato) LIKE LOWER(CONCAT('%', :param, '%')) " +
            "OR LOWER(c.numeroParcela) LIKE LOWER(CONCAT('%', :param, '%')) " +
            "OR LOWER(c.status) LIKE LOWER(CONCAT('%', :param, '%'))")
    Page<ContratoParcela> searchByKeyword(@Param("param") String param, Pageable pageable);



}
