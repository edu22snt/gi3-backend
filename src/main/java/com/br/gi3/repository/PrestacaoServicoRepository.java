package com.br.gi3.repository;

import com.br.gi3.model.PrestacaoServico;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestacaoServicoRepository extends JpaRepository<PrestacaoServico, Long> {

    @Query("SELECT t FROM PrestacaoServico t WHERE " +
            "LOWER(t.vendedor) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.contrato) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.parcela) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.empresa) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<PrestacaoServico> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
