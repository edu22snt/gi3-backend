package com.br.gi3.service;

import com.br.gi3.model.PrestacaoServico;
import com.br.gi3.repository.PrestacaoServicoRepository;
import com.br.gi3.service.dto.PrestacaoServicoDTO;
import com.br.gi3.service.mapper.PrestacaoServicoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PrestacaoServicoService {

    private final Logger log = LoggerFactory.getLogger(PrestacaoServicoService.class);
    private PrestacaoServicoRepository prestacaoServicoRepository;
    private PrestacaoServicoMapper prestacaoServicoMapper;

    public PrestacaoServicoService(
            PrestacaoServicoRepository prestacaoServicoRepository,
            PrestacaoServicoMapper prestacaoServicoMapper
    ) {
        this.prestacaoServicoRepository = prestacaoServicoRepository;
        this.prestacaoServicoMapper = prestacaoServicoMapper;

    }

    public PrestacaoServicoDTO save(PrestacaoServicoDTO prestacaoServicoDTO) {
        log.debug("Request to post save PrestacaoServico");
        PrestacaoServico prestacaoServico = prestacaoServicoMapper.toEntity(prestacaoServicoDTO);
        prestacaoServico = prestacaoServicoRepository.save(prestacaoServico);
        return prestacaoServicoMapper.toDto(prestacaoServico);
    }

    @Transactional(readOnly = true)
    public Optional<PrestacaoServicoDTO> findOne(Long id) {
        log.debug("Request to get one PrestacaoServico by id");
        return prestacaoServicoRepository.findById(id).map(PrestacaoServicoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<PrestacaoServicoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrestacaoServico");
        return prestacaoServicoRepository.findAll(pageable).map(PrestacaoServicoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<PrestacaoServicoDTO> searchByKeyword(String param, Pageable pageable) {
        log.debug("Request to get all PrestacaoServico");
        return prestacaoServicoRepository.searchByKeyword(param, pageable).map(PrestacaoServicoMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete PrestacaoServico by id : {}", id);
        prestacaoServicoRepository.deleteById(id);
    }

    public PrestacaoServicoDTO update(PrestacaoServicoDTO prestacaoServicoDTO) {
        log.debug("Request to update PrestacaoServico: {}", prestacaoServicoDTO);
        PrestacaoServico prestacaoServico = prestacaoServicoMapper.toEntity(prestacaoServicoDTO);
        prestacaoServico = prestacaoServicoRepository.save(prestacaoServico);
        return prestacaoServicoMapper.toDto(prestacaoServico);
    }

}
