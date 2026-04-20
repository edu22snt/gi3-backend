package com.br.gi3.controller;

import com.br.gi3.service.PrestacaoServicoService;
import com.br.gi3.service.dto.PrestacaoServicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/prestacaoServico")
public class PrestacaoServicoController {

    private PrestacaoServicoService service;

    PrestacaoServicoController(PrestacaoServicoService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<PrestacaoServicoDTO> save(@RequestBody PrestacaoServicoDTO prestacaoServicoDTO) throws URISyntaxException {
        PrestacaoServicoDTO result = service.save(prestacaoServicoDTO);
        return ResponseEntity.created(new URI("/api/save/" + result.getId())).body(prestacaoServicoDTO);
    }

    @GetMapping("/repasse/{id}")
    public Optional<PrestacaoServicoDTO> findById(@PathVariable Long id) {
        Optional<PrestacaoServicoDTO> prestacaoServicoDTO = service.findOne(id);
        return prestacaoServicoDTO;
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<PrestacaoServicoDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/searchByKeyword")
    public ResponseEntity<Page<PrestacaoServicoDTO>> searchByKeyword(@RequestParam String param, Pageable pageable) {
        return ResponseEntity.ok(service.searchByKeyword(param, pageable));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<PrestacaoServicoDTO> update(@RequestBody PrestacaoServicoDTO prestacaoServicoDTO) {
        PrestacaoServicoDTO result = service.update(prestacaoServicoDTO);
        return ResponseEntity.ok().body(result);
    }

}
