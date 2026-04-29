package com.br.gi3.controller;

import com.br.gi3.service.VendedorService;
import com.br.gi3.service.dto.VendedorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendedor")
public class VenderorController {

    private VendedorService service;

    VenderorController(VendedorService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<VendedorDTO> create(@RequestBody VendedorDTO dto) throws URISyntaxException {
        if (service.findByName(dto.getNome()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
        }
        VendedorDTO result = service.create(dto);
        return ResponseEntity.created(new URI("/api/save/" + result.getId())).body(dto);
    }

    @GetMapping("/findById/{id}")
    public Optional<VendedorDTO> findById(@PathVariable Long id) {
        Optional<VendedorDTO> vendedor = service.findById(id);
        return vendedor;
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<VendedorDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/searchByKeyword")
    public ResponseEntity<Page<VendedorDTO>> searchByKeyword(@RequestParam String param, Pageable pageable) {
        return ResponseEntity.ok(service.searchByKeyword(param, pageable));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<VendedorDTO> update(@RequestBody VendedorDTO dto) {
        VendedorDTO result = service.update(dto);
        return ResponseEntity.ok().body(result);
    }
}
