package com.br.gi3.service;

import com.br.gi3.model.Vendedor;
import com.br.gi3.repository.VendedorRepository;
import com.br.gi3.service.dto.VendedorDTO;
import com.br.gi3.service.mapper.VendedorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class VendedorService {

    private final Logger log = LoggerFactory.getLogger(VendedorService.class);
    private final VendedorRepository repository;
    private final VendedorMapper vendedorMapper;

    public VendedorService(VendedorRepository repository, VendedorMapper vendedorMapper) {
        this.repository = repository;
        this.vendedorMapper = vendedorMapper;
    }

    public VendedorDTO create(VendedorDTO dto) {
        log.debug("Request to post create Vendedor");
        Vendedor entity = vendedorMapper.toEntity(dto);
        entity = repository.save(entity);
        return vendedorMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<VendedorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vendedor");
        return repository.findAll(pageable).map(VendedorMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<VendedorDTO> findById(Long id) {
        log.debug("Request to get one Vendedor by id");
        return repository.findById(id).map(VendedorMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<VendedorDTO> findByName(String username) {
        log.debug("Request to get one Vendedor by name");
        return repository.findByNome(username).map(VendedorMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<VendedorDTO> searchByKeyword(String param, Pageable pageable) {
        log.debug("Request to get all nome vendedor");
        return repository.searchByKeyword(param, pageable).map(VendedorMapper::toDto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public VendedorDTO update(VendedorDTO dto) {
        log.debug("Request to update Usuario: {}", dto);
        Vendedor vendedor = vendedorMapper.toEntity(dto);
        vendedor = repository.save(vendedor);
        return vendedorMapper.toDto(vendedor);
    }
}
