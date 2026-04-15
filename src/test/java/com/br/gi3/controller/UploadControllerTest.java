package com.br.gi3.controller;

import com.br.gi3.service.UploadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UploadControllerTest {

    private UploadService uploadService;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        uploadService = Mockito.mock(UploadService.class);
        UploadController controller = new UploadController(uploadService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    private MockMultipartFile getMockFile() {
        return new MockMultipartFile(
                "file",
                "test.xlsx",
                MediaType.APPLICATION_OCTET_STREAM_VALUE,
                "conteudo teste".getBytes()
        );
    }

    @Test
    void deveImportarArquivoBancorbrasComSucesso() throws Exception {
        doNothing().when(uploadService).importFileBancorbras(any());

        mockMvc.perform(multipart("/api/upload/bancorbras")
                .file(getMockFile()))
                .andExpect(status().isOk())
                .andExpect(content().string("Arquivo de repasse Bancorbras importado com sucesso"));
    }

    @Test
    void deveRetornarErroAoImportarBancorbras() throws Exception {
        doThrow(new RuntimeException("Erro teste"))
                .when(uploadService).importFileBancorbras(any());

        mockMvc.perform(multipart("/api/upload/bancorbras")
                .file(getMockFile()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Erro ao importar a planilha de repasse Bancorbras: Erro teste"));
    }

    @Test
    void deveImportarArquivoHsComSucesso() throws Exception {
        doNothing().when(uploadService).importFileHs(any());

        mockMvc.perform(multipart("/api/upload/hs")
                .file(getMockFile()))
                .andExpect(status().isOk())
                .andExpect(content().string("Arquivo de repasse HS importado com sucesso"));
    }

    @Test
    void deveRetornarErroAoImportarHs() throws Exception {
        doThrow(new RuntimeException("Erro teste"))
                .when(uploadService).importFileHs(any());

        mockMvc.perform(multipart("/api/upload/hs")
                .file(getMockFile()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Erro ao importar a planilha de repasse HS: Erro teste"));
    }

    @Test
    void deveImportarArquivoPrestacaoServicoComSucesso() throws Exception {
        doNothing().when(uploadService).importFilePrestacaoServico(any());

        mockMvc.perform(multipart("/api/upload/prestacaoServico")
                .file(getMockFile()))
                .andExpect(status().isOk())
                .andExpect(content().string("Arquivo de prestação de serviço importado com sucesso"));
    }

    @Test
    void deveRetornarErroAoImportarPrestacaoServico() throws Exception {
        doThrow(new RuntimeException("Erro teste"))
                .when(uploadService).importFilePrestacaoServico(any());

        mockMvc.perform(multipart("/api/upload/prestacaoServico")
                .file(getMockFile()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Erro ao importar a planilha de prestação de serviço: Erro teste"));
    }
}