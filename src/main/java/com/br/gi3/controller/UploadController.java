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

    private static final String SUCCESS_TRANSFER_BANCORBRAS_IMPORTED = "Arquivo de repasse Bancorbras importado com sucesso";
    private static final String ERROR_TRANSFER_BANCORBRAS_IMPORTED = "Erro ao importar a planilha de repasse Bancorbras: ";

    private static final String SUCCESS_TRANSFER_HS_IMPORTED = "Arquivo de repasse HS importado com sucesso";
    private static final String ERROR_TRANSFER_HS_IMPORTED = "Erro ao importar a planilha de repasse HS: ";

    private static final String SUCCESS_TRANSFER_IMPORTED_PAYMENT = "Arquivo de prestação de serviço importado com sucesso";
    private static final String ERROR_TRANSFER_IMPORTED_PAYMENT = "Erro ao importar a planilha de prestação de serviço: ";

    UploadController(UploadService service) {
        this.service = service;
    }

    @PostMapping("/bancorbras")
    public ResponseEntity<String> uploadFileRepasseBancorbras(@RequestParam("file") MultipartFile file) {

        try {
            service.importFileBancorbras(file);
            return ResponseEntity.ok(SUCCESS_TRANSFER_BANCORBRAS_IMPORTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR_TRANSFER_BANCORBRAS_IMPORTED + e.getMessage());
        }
    }

    @PostMapping("/hs")
    public ResponseEntity<String> uploadFileRepasseHs(@RequestParam("file") MultipartFile file) {

        try {
            service.importFileHs(file);
            return ResponseEntity.ok(SUCCESS_TRANSFER_HS_IMPORTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR_TRANSFER_HS_IMPORTED + e.getMessage());
        }
    }

    @PostMapping("/prestacaoServico")
    public ResponseEntity<String> uploadFilePrestacaoServico(@RequestParam("file") MultipartFile file) {

        try {
            service.importFilePrestacaoServico(file);
            return ResponseEntity.ok(SUCCESS_TRANSFER_IMPORTED_PAYMENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR_TRANSFER_IMPORTED_PAYMENT + e.getMessage());
        }
    }
}
