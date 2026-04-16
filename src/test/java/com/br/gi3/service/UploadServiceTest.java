package com.br.gi3.service;

import static org.junit.jupiter.api.Assertions.*;

import com.br.gi3.repository.*;
import com.br.gi3.service.mapper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.mock.web.MockMultipartFile;

import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UploadServiceTest {

    @InjectMocks
    private UploadService uploadService;

    @Mock
    private RepasseBancorbrasRepository repasseBancorbrasRepository;

    @Mock
    private RepasseBancorbrasMapper repasseBancorbrasMapper;

    @Mock
    private RepasseHsRepository repasseHsRepository;

    @Mock
    private RepasseHsMapper repasseHsMapper;

    @Mock
    private PrestacaoServicoRepository prestacaoServicoRepository;

    @Mock
    private PrestacaoServicoMapper prestacaoServicoMapper;

    private MockMultipartFile csvFile;
    private MockMultipartFile invalidFile;

    @BeforeEach
    void setup() {
        csvFile = new MockMultipartFile(
                "file",
                "teste.csv",
                "text/csv",
                "nome,email\nJohn,john@email.com".getBytes()
        );

        invalidFile = new MockMultipartFile(
                "file",
                "teste.txt",
                "text/plain",
                "conteudo".getBytes()
        );
    }

    @Test
    void deveProcessarCSV_Bancorbras() throws Exception {
        assertDoesNotThrow(() -> uploadService.importFileBancorbras(csvFile));
    }

    @Test
    void deveProcessarCSV_Hs() throws Exception {
        assertDoesNotThrow(() -> uploadService.importFileHs(csvFile));
    }

    @Test
    void deveProcessarCSV_PrestacaoServico() throws Exception {
        assertDoesNotThrow(() -> uploadService.importFilePrestacaoServico(csvFile));
    }

    @Test
    void deveLancarErroFormatoInvalido_Bancorbras() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> uploadService.importFileBancorbras(invalidFile));

        assertEquals("Formato de arquivo não suportado", ex.getMessage());
    }

    @Test
    void deveProcessarExcelSemErro() throws Exception {
        byte[] excelContent = new byte[]{1,2,3};
        MockMultipartFile excelFile = new MockMultipartFile(
                "file",
                "teste.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                excelContent
        );
        assertThrows(Exception.class, () ->
                uploadService.importFileBancorbras(excelFile)
        );
    }

    @Test
    void deveChamarMapperESave_Bancorbras() throws Exception {
        uploadService.importFileBancorbras(csvFile);
        verify(repasseBancorbrasRepository, never()).save(any());
    }

}