package com.br.gi3.controller;

import com.br.gi3.service.ContratoService;
import com.br.gi3.service.dto.ContratoDTO;
import com.br.gi3.service.dto.PrestacaoServicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/contrato")
public class ContratoController {

    private final ContratoService service;

    public ContratoController(ContratoService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ContratoDTO> create(@RequestBody ContratoDTO dto) throws URISyntaxException {
        ContratoDTO result = service.create(dto);
        return ResponseEntity.created(new URI("/api/save/" + result.getId())).body(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<ContratoDTO> update(@RequestBody ContratoDTO dto) {
        ContratoDTO result = service.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ContratoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<ContratoDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/searchByKeyword")
    public ResponseEntity<Page<ContratoDTO>> searchByKeyword(@RequestParam String param, Pageable pageable) {
        return ResponseEntity.ok(service.searchByKeyword(param, pageable));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}