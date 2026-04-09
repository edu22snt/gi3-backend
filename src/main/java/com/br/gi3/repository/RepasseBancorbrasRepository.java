package com.br.gi3.repository;

import com.br.gi3.model.RepasseBancorbras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepasseBancorbrasRepository extends JpaRepository<RepasseBancorbras, Long> {
}
