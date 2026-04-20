package com.br.gi3.controller;

import com.br.gi3.service.RepasseService;
import com.br.gi3.service.dto.RepasseBancorbrasDTO;
import com.br.gi3.service.dto.RepasseHsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/repasse")
public class RepasseController {

    private RepasseService service;

    RepasseController(RepasseService service) {
        this.service = service;
    }

    @PostMapping("/saveHs")
    public ResponseEntity<RepasseHsDTO> saveHs(@RequestBody RepasseHsDTO repasseHsDTO) throws URISyntaxException {
        RepasseHsDTO result = service.saveHs(repasseHsDTO);
        return ResponseEntity.created(new URI("/api/saveHs/" + result.getId())).body(repasseHsDTO);
    }

    @GetMapping("/repasseHs/{id}")
    public Optional<RepasseHsDTO> findByIdHs(@PathVariable Long id) {
        Optional<RepasseHsDTO> repasseHsDTO = service.findOneHs(id);
        return repasseHsDTO;
    }

    @GetMapping("/findAllHs")
    public ResponseEntity<Page<RepasseHsDTO>> findAllHs(Pageable pageable) {
        return ResponseEntity.ok(service.findAllHs(pageable));
    }

    @GetMapping("/searchByKeywordHs")
    public ResponseEntity<Page<RepasseHsDTO>> searchByKeyword(@RequestParam String param, Pageable pageable) {
        return ResponseEntity.ok(service.searchByKeywordHs(param, pageable));
    }

    @DeleteMapping("/deleteHs/{id}")
    public ResponseEntity<Void> deleteHs(@PathVariable Long id) {
        service.deleteHs(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateHs")
    public ResponseEntity<RepasseHsDTO> updateHs(@RequestBody RepasseHsDTO repasseHsDTO) {
        RepasseHsDTO result = service.updateHs(repasseHsDTO);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/saveBancorbras")
    public ResponseEntity<RepasseBancorbrasDTO> saveBancorbras(@RequestBody RepasseBancorbrasDTO repasseBancorbrasDTO) throws URISyntaxException {
        RepasseBancorbrasDTO result = service.saveBancorbras(repasseBancorbrasDTO);
        return ResponseEntity.created(new URI("/api/saveBancorbras/" + result.getId())).body(repasseBancorbrasDTO);
    }

    @GetMapping("/repasseBancorbras/{id}")
    public Optional<RepasseBancorbrasDTO> findByIdBancorbras(@PathVariable Long id) {
        Optional<RepasseBancorbrasDTO> repasseBancorbrasDTO = service.findOneBancorbras(id);
        return repasseBancorbrasDTO;
    }

    @GetMapping("/findAllBancorbras")
    public ResponseEntity<Page<RepasseBancorbrasDTO>> findAllBancorbras(Pageable pageable) {
        return ResponseEntity.ok(service.findAllBancorbras(pageable));
    }

    @GetMapping("/searchByKeywordBancorbras")
    public ResponseEntity<Page<RepasseBancorbrasDTO>> searchByKeywordBancorbras(@RequestParam String param, Pageable pageable) {
        return ResponseEntity.ok(service.searchByKeywordBancorbras(param, pageable));
    }

    @DeleteMapping("/deleteBancorbras/{id}")
    public ResponseEntity<Void> deleteBancorbras(@PathVariable Long id) {
        service.deleteBancorbras(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateBancorbras")
    public ResponseEntity<RepasseBancorbrasDTO> updateBancorbras(@RequestBody RepasseBancorbrasDTO repasseBancorbrasDTO) {
        RepasseBancorbrasDTO result = service.updateBancorbras(repasseBancorbrasDTO);
        return ResponseEntity.ok().body(result);
    }
}
