package com.br.gi3.repository;

import com.br.gi3.model.RepasseBancorbras;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepasseBancorbrasRepository extends JpaRepository<RepasseBancorbras, Long> {

    @Query("SELECT t FROM RepasseBancorbras t WHERE " +
            "LOWER(t.cliente) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.contrato) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.parcela) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.bem) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.pg) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<RepasseBancorbras> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
