package com.br.gi3.controller;

import com.br.gi3.service.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private UploadService service;

    UploadController(UploadService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            service.importFile(file);
            return ResponseEntity.ok("Arquivo importado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao importar: " + e.getMessage());
        }

    }
}
