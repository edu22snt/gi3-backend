package com.br.gi3.controller;

import com.br.gi3.service.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private UploadService service;

    UploadController(UploadService service) {
        this.service = service;
    }

    @PostMapping("/bancorbras")
    public ResponseEntity<String> uploadFileRepasseBancorbras(@RequestParam("file") MultipartFile file) {

        try {
            service.importFileBancorbras(file);
            return ResponseEntity.ok("Arquivo de repasse Bancorbras importado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao importar a planilha de repasse Bancorbras: " + e.getMessage());
        }
    }

    @PostMapping("/hs")
    public ResponseEntity<String> uploadFileRepasseHs(@RequestParam("file") MultipartFile file) {

        try {
            service.importFileHs(file);
            return ResponseEntity.ok("Arquivo de repasse HS importado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao importar a planilha de repasse HS: " + e.getMessage());
        }
    }

    @PostMapping("/pestacaoServico")
    public ResponseEntity<String> uploadFilePrestacaoServico(@RequestParam("file") MultipartFile file) {

        try {
            service.importFilePrestacaoServico(file);
            return ResponseEntity.ok("Arquivo de prestação de serviço importado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao importar a planilha de prestação de serviço: " + e.getMessage());
        }
    }
}
