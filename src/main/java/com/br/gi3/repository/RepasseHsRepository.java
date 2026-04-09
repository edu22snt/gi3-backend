package com.br.gi3.repository;

import com.br.gi3.model.RepasseHs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepasseHsRepository extends JpaRepository<RepasseHs, Long> {
}
