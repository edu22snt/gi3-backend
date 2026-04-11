package com.br.gi3.repository;

import com.br.gi3.model.PrestacaoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestacaoServicoRepository extends JpaRepository<PrestacaoServico, Long> {
}
