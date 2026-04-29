package com.br.gi3.service;

import com.br.gi3.model.ContratoParcela;
import com.br.gi3.repository.ContratoParcelaRepository;
import com.br.gi3.service.dto.ContratoParcelaDTO;
import com.br.gi3.service.dto.RepasseBancorbrasDTO;
import com.br.gi3.service.dto.RepasseHsDTO;
import com.br.gi3.service.mapper.ContratoParcelaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class ContratoParcelaService {

    private final Logger log = LoggerFactory.getLogger(PrestacaoServicoService.class);
    private final ContratoParcelaRepository repository;
    private ContratoParcelaMapper contratoMapper;

    public ContratoParcelaService(ContratoParcelaRepository repository, ContratoParcelaMapper contratoMapper) {
        this.repository = repository;
        this.contratoMapper = contratoMapper;
    }

    public ContratoParcelaDTO create(ContratoParcelaDTO dto) {
        log.debug("Request to post create ContratoParcela");
        ContratoParcela entity = contratoMapper.toEntity(dto);
        entity = repository.save(entity);
        return contratoMapper.toDto(entity);
    }

    public ContratoParcelaDTO update(ContratoParcelaDTO dto) {
        log.debug("Request to update ContratoParcela : {}", dto);
        if (dto.getId() == null) {
            throw new RuntimeException("ID não pode ser nulo para atualização");
        }

        ContratoParcela entity = repository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Parcela não encontrada"));

        entity.setId(dto.getId());
        entity.setNumeroParcela(dto.getNumeroParcela());
        entity.setStatus(dto.getStatus());
        entity.setNumeroParcela(dto.getNumeroParcela());

        return ContratoParcelaMapper.toDto(repository.save(entity));
    }

    @Transactional
    public ContratoParcelaDTO updateStatusParcelaBancorbras(RepasseBancorbrasDTO dto) {
        ContratoParcela entity = repository
                .findByNumeroContrato(dto.getContrato(), dto.getParcela())
                .orElseThrow(() -> new RuntimeException("Parcela não encontrada"));
        entity.setStatus("OK");
        entity.setPorcentagemComissao(dto.getComissaoVendedor());
        entity.setBase(dto.getValorBase());
        entity.setLiquido(dto.getComissaoLiquida());
        entity.setComissao(dto.getComissaoVendedor());

        return ContratoParcelaMapper.toDto(repository.save(entity));
    }

    public ContratoParcelaDTO updateStatusParcelaHs(RepasseHsDTO dto) {
        ContratoParcela entity = repository.findByNumeroContrato(dto.getContrato(), dto.getParcela())
                .orElseThrow(() -> new RuntimeException("Parcela não encontrada"));

        entity.setStatus("OK");
        entity.setPorcentagemComissao(dto.getComissaoVendedor());
        entity.setComissao(dto.getComissaoVendedor());
        return ContratoParcelaMapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public ContratoParcelaDTO findById(Long id) {
        log.debug("Request to get ContratoParcela by id : {}", id);
        return repository.findById(id)
                .map(ContratoParcelaMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
    }

    @Transactional(readOnly = true)
    public Page<ContratoParcelaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ContratoParcela");
        return repository.findAll(pageable)
                .map(ContratoParcelaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ContratoParcelaDTO> searchByKeyword(String param, Pageable pageable) {
        log.debug("Request to get search ContratoParcela by keyword");
        return repository.searchByKeyword(param, pageable).map(ContratoParcelaMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete ContratoParcela by id : {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("Parcela não encontrada");
        }
        repository.deleteById(id);
    }

}
