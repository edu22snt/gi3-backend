package com.br.gi3.service;

import com.br.gi3.model.RepasseBancorbras;
import com.br.gi3.model.RepasseHs;
import com.br.gi3.repository.RepasseBancorbrasRepository;
import com.br.gi3.repository.RepasseHsRepository;
import com.br.gi3.service.dto.RepasseBancorbrasDTO;
import com.br.gi3.service.dto.RepasseHsDTO;
import com.br.gi3.service.mapper.RepasseBancorbrasMapper;
import com.br.gi3.service.mapper.RepasseHsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RepasseService {

    private final Logger log = LoggerFactory.getLogger(RepasseService.class);
    private RepasseBancorbrasRepository repasseBancorbrasRepository;
    private RepasseBancorbrasMapper repasseBancorbrasMapper;
    private RepasseHsRepository repasseHsRepository;
    private RepasseHsMapper repasseHsMapper;

    public RepasseService(
            RepasseBancorbrasRepository repasseBancorbrasRepository,
            RepasseBancorbrasMapper repasseBancorbrasMapper,
            RepasseHsRepository repasseHsRepository,
            RepasseHsMapper repasseHsMapper
    ) {
        this.repasseBancorbrasRepository = repasseBancorbrasRepository;
        this.repasseBancorbrasMapper = repasseBancorbrasMapper;
        this.repasseHsRepository = repasseHsRepository;
        this.repasseHsMapper = repasseHsMapper;
    }

    public RepasseHsDTO saveHs(RepasseHsDTO repasseHsDTO) {
        log.debug("Request to post save RepasseHsDTO");
        RepasseHs repasseHs = repasseHsMapper.toEntity(repasseHsDTO);
        repasseHs = repasseHsRepository.save(repasseHs);
        return repasseHsMapper.toDto(repasseHs);
    }

    @Transactional(readOnly = true)
    public Optional<RepasseHsDTO> findOneHs(Long id) {
        log.debug("Request to get one RepasseHs by id");
        return repasseHsRepository.findById(id).map(RepasseHsMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<RepasseHsDTO> findAllHs(Pageable pageable) {
        log.debug("Request to get all RepasseHs");
        return repasseHsRepository.findAll(pageable).map(RepasseHsMapper::toDto);
    }

    public void deleteHs(Long id) {
        log.debug("Request to delete RepasseHs by id : {}", id);
        repasseHsRepository.deleteById(id);
    }

    public RepasseHsDTO updateHs(RepasseHsDTO repasseHsDTO) {
        log.debug("Request to update RepasseHs: {}", repasseHsDTO);
        RepasseHs repasseHs = repasseHsMapper.toEntity(repasseHsDTO);
        repasseHs = repasseHsRepository.save(repasseHs);
        return repasseHsMapper.toDto(repasseHs);
    }

    public RepasseBancorbrasDTO saveBancorbras(RepasseBancorbrasDTO repasseBancorbrasDTO) {
        log.debug("Request to post save RepasseHsDTO");
        RepasseBancorbras repasseBancorbras = repasseBancorbrasMapper.toEntity(repasseBancorbrasDTO);
        repasseBancorbras = repasseBancorbrasRepository.save(repasseBancorbras);
        return repasseBancorbrasMapper.toDto(repasseBancorbras);
    }

    @Transactional(readOnly = true)
    public Optional<RepasseBancorbrasDTO> findOneBancorbras(Long id) {
        log.debug("Request to get one RepasseHs by id");
        return repasseBancorbrasRepository.findById(id).map(RepasseBancorbrasMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<RepasseBancorbrasDTO> findAllBancorbras(Pageable pageable) {
        log.debug("Request to get all RepasseHs");
        return repasseBancorbrasRepository.findAll(pageable).map(RepasseBancorbrasMapper::toDto);
    }

    public void deleteBancorbras(Long id) {
        log.debug("Request to delete RepasseHs by id : {}", id);
        repasseHsRepository.deleteById(id);
    }

    public RepasseBancorbrasDTO updateBancorbras(RepasseBancorbrasDTO repasseHsDTO) {
        log.debug("Request to update RepasseHs: {}", repasseHsDTO);
        RepasseBancorbras repasseBancorbras = repasseBancorbrasMapper.toEntity(repasseHsDTO);
        repasseBancorbras = repasseBancorbrasRepository.save(repasseBancorbras);
        return repasseBancorbrasMapper.toDto(repasseBancorbras);
    }

}
