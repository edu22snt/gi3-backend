package com.br.gi3.service;

import com.br.gi3.model.Contrato;
import com.br.gi3.model.ContratoParcela;
import com.br.gi3.repository.ContratoRepository;
import com.br.gi3.service.dto.ContratoDTO;
import com.br.gi3.service.mapper.ContratoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContratoService {

    private final Logger log = LoggerFactory.getLogger(PrestacaoServicoService.class);
    private final ContratoRepository repository;
    private ContratoMapper contratoMapper;

    public ContratoService(ContratoRepository repository, ContratoMapper contratoMapper) {
        this.repository = repository;
        this.contratoMapper = contratoMapper;
    }

    public ContratoDTO create(ContratoDTO dto) {
        log.debug("Request to post create Contrato");
        Contrato entity = contratoMapper.toEntity(dto);
        entity = repository.save(entity);
        return contratoMapper.toDto(entity);
    }

    public ContratoDTO update(ContratoDTO dto) {
        log.debug("Request to update Contrato : {}", dto);
        if (dto.getId() == null) {
            throw new RuntimeException("ID não pode ser nulo para atualização");
        }

        Contrato entity = repository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));

        entity.setNumeroContrato(dto.getNumeroContrato());
        entity.setVendedor(dto.getVendedor());
        entity.setTipo(dto.getTipo());
        entity.setEmpresa(dto.getEmpresa());
        entity.setValor(dto.getValor());

        entity.clearParcelas();

        if (dto.getParcelas() != null) {
            dto.getParcelas().forEach(p -> {
                ContratoParcela parcela = new ContratoParcela();
                parcela.setNumeroParcela(p.getNumeroParcela());
                parcela.setStatus(p.getStatus());
                entity.addParcela(parcela);
            });
        }
        return ContratoMapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public ContratoDTO findById(Long id) {
        log.debug("Request to get Contrato by id : {}", id);
        return repository.findById(id)
                .map(ContratoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
    }

    @Transactional(readOnly = true)
    public Page<ContratoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Contrato");
        return repository.findAll(pageable)
                .map(ContratoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ContratoDTO> searchByKeyword(String param, Pageable pageable) {
        log.debug("Request to get search Contrato by keyword");
        return repository.searchByKeyword(param, pageable).map(ContratoMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Contrato by id : {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("Contrato não encontrado");
        }
        repository.deleteById(id);
    }
}
