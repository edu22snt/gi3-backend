package com.br.gi3.controller;

import com.br.gi3.service.ContratoParcelaService;
import com.br.gi3.service.dto.ContratoParcelaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/contrato-parcela")
public class ContratoParcelaController {

    private final ContratoParcelaService service;

    public ContratoParcelaController(ContratoParcelaService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ContratoParcelaDTO> create(@RequestBody ContratoParcelaDTO dto) throws URISyntaxException {
        ContratoParcelaDTO result = service.create(dto);
        return ResponseEntity.created(new URI("/api/save/" + result.getId())).body(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<ContratoParcelaDTO> update(@RequestBody ContratoParcelaDTO dto) {
        ContratoParcelaDTO result = service.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ContratoParcelaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<ContratoParcelaDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/searchByKeyword")
    public ResponseEntity<Page<ContratoParcelaDTO>> searchByKeyword(@RequestParam String param, Pageable pageable) {
        return ResponseEntity.ok(service.searchByKeyword(param, pageable));
    }

    @GetMapping("/searchByNumeroContrato")
    public ResponseEntity<Page<ContratoParcelaDTO>> searchByNumeroContrato(@RequestParam String param, Pageable pageable) {
        return ResponseEntity.ok(service.searchByNumeroContrato(param, pageable));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
