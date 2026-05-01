package com.br.gi3.controller;

import com.br.gi3.service.*;
import com.br.gi3.service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {

    private RelatorioPrestacaoServicoService relatorioPrestacaoServicoService;
    private PrestacaoServicoService prestacaoServicoService;
    private RepasseService repasseService;
    private ContratoService contratoService;
    private VendedorService vendedorService;

    public RelatorioController(
        RelatorioPrestacaoServicoService relatorioPrestacaoServicoService,
        PrestacaoServicoService prestacaoServicoService,
        RepasseService repasseService,
        ContratoService contratoService,
        VendedorService vendedorService
    ) {
        this.relatorioPrestacaoServicoService = relatorioPrestacaoServicoService;
        this.prestacaoServicoService = prestacaoServicoService;
        this.repasseService = repasseService;
        this.contratoService = contratoService;
        this.vendedorService = vendedorService;
    }

    @GetMapping("/prestacaoServico")
    public ResponseEntity<byte[]> gerarRelatorioPrestacaoServico(@RequestParam String param, Pageable pageable) {
        Page<PrestacaoServicoDTO> page = prestacaoServicoService.searchByKeyword(param, pageable);
        ByteArrayInputStream pdf = relatorioPrestacaoServicoService.gerarRelatorioPrestacaoServico(page.getContent(), param);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }

    @GetMapping("/bancorbras")
    public ResponseEntity<byte[]> gerarRelatorioBancorbras(@RequestParam String param, Pageable pageable) {
        Page<RepasseBancorbrasDTO> page = repasseService.searchByKeywordBancorbras(param, pageable);
        ByteArrayInputStream pdf = relatorioPrestacaoServicoService.gerarRelatorioBancorbras(page.getContent(), param);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }

    @GetMapping("/hs")
    public ResponseEntity<byte[]> gerarRelatorioHs(@RequestParam String param, Pageable pageable) {
        Page<RepasseHsDTO> page = repasseService.searchByKeywordHs(param, pageable);
        ByteArrayInputStream pdf = relatorioPrestacaoServicoService.gerarRelatorioHs(page.getContent(), param);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }

    @GetMapping("/contratos")
    public ResponseEntity<byte[]> gerarRelatorioContratos(@RequestParam String param, Pageable pageable) {
        Page<ContratoDTO> page = contratoService.searchByKeyword(param, pageable);
        ByteArrayInputStream pdf = relatorioPrestacaoServicoService.gerarRelatorioContratos(page.getContent(), param);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }

    @GetMapping("/vendedores")
    public ResponseEntity<byte[]> gerarRelatorioVendedores(@RequestParam String param, Pageable pageable) {
        Page<VendedorDTO> page = vendedorService.searchByKeyword(param, pageable);
        ByteArrayInputStream pdf = relatorioPrestacaoServicoService.gerarRelatorioVendedores(page.getContent(), param);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }
}
