package com.br.gi3.service;

import com.br.gi3.enumerate.TipoPlanilhaEnum;
import com.br.gi3.model.PrestacaoServico;
import com.br.gi3.model.RepasseBancorbras;
import com.br.gi3.model.RepasseHs;
import com.br.gi3.repository.PrestacaoServicoRepository;
import com.br.gi3.repository.RepasseBancorbrasRepository;
import com.br.gi3.repository.RepasseHsRepository;
import com.br.gi3.service.dto.PrestacaoServicoDTO;
import com.br.gi3.service.dto.RepasseBancorbrasDTO;
import com.br.gi3.service.dto.RepasseHsDTO;
import com.br.gi3.service.mapper.PrestacaoServicoMapper;
import com.br.gi3.service.mapper.RepasseBancorbrasMapper;
import com.br.gi3.service.mapper.RepasseHsMapper;
import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.math.BigDecimal;

@Service
@Transactional
public class UploadService {

    private final Logger log = LoggerFactory.getLogger(UploadService.class);
    private RepasseBancorbrasRepository repasseBancorbrasRepository;
    private RepasseBancorbrasMapper repasseBancorbrasMapper;
    private RepasseHsRepository repasseHsRepository;
    private RepasseHsMapper repasseHsMapper;
    private PrestacaoServicoRepository prestacaoServicoRepository;
    private PrestacaoServicoMapper prestacaoServicoMapper;

    private static final String CSV = ".csv";
    private static final String XLSX = ".xlsx";
    private static final String XLS = ".xls";
    private static final String FORMAT_NOT_SUPPORTED = "Formato de arquivo não suportado";

    public UploadService(
            RepasseBancorbrasRepository repasseBancorbrasRepository,
            RepasseBancorbrasMapper repasseBancorbrasMapper,
            RepasseHsRepository repasseHsRepository,
            RepasseHsMapper repasseHsMapper,
            PrestacaoServicoRepository prestacaoServicoRepository,
            PrestacaoServicoMapper prestacaoServicoMapper
    ) {
        this.repasseBancorbrasRepository = repasseBancorbrasRepository;
        this.repasseBancorbrasMapper = repasseBancorbrasMapper;
        this.repasseHsRepository = repasseHsRepository;
        this.repasseHsMapper = repasseHsMapper;
        this.prestacaoServicoRepository = prestacaoServicoRepository;
        this.prestacaoServicoMapper = prestacaoServicoMapper;

    }

    public void importFileBancorbras(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        if (filename.endsWith(CSV)) {
            readCSV(file);
        } else if (filename.endsWith(XLSX) || filename.endsWith(XLS)) {
            readExcel(file, TipoPlanilhaEnum.BANCORBRAS);
        } else {
            throw new RuntimeException(FORMAT_NOT_SUPPORTED);
        }
    }

    public void importFileHs(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        if (filename.endsWith(CSV)) {
            readCSV(file);
        } else if (filename.endsWith(XLSX) || filename.endsWith(XLS)) {
            readExcel(file, TipoPlanilhaEnum.HS);
        } else {
            throw new RuntimeException(FORMAT_NOT_SUPPORTED);
        }
    }

    public void importFilePrestacaoServico(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        if (filename.endsWith(CSV)) {
            readCSV(file);
        } else if (filename.endsWith(XLSX) || filename.endsWith(XLS)) {
            readExcel(file, TipoPlanilhaEnum.PESTACAO_SERVICO);
        } else {
            throw new RuntimeException(FORMAT_NOT_SUPPORTED);
        }
    }

    private void readCSV(MultipartFile file) throws Exception {
        CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()));
        String[] line;
        while ((line = reader.readNext()) != null) {
            String nome = line[0];
            String email = line[1];
            System.out.println(nome + " - " + email);
        }
        reader.close();
    }

    private void readExcel(MultipartFile file, TipoPlanilhaEnum tipoPlanilha) throws Exception {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            if (tipoPlanilha == TipoPlanilhaEnum.BANCORBRAS) {
                this.popularCellBancorbras(row);
            } else if (tipoPlanilha == TipoPlanilhaEnum.HS) {
                this.popularCellHs(row);
            } else if (tipoPlanilha == TipoPlanilhaEnum.PESTACAO_SERVICO) {
                this.popularCellPrestacaoServico(row);
            }
        }
        workbook.close();
    }

    private void popularCellBancorbras(Row row) {
        RepasseBancorbrasDTO repasseBancorbrasDTO = new RepasseBancorbrasDTO();
        DataFormatter formatter = new DataFormatter();

        Cell cellCliente = row.getCell(0);
        Cell cellContrato = row.getCell(1);
        Cell cellVenda = row.getCell(2);
        Cell cellMes = row.getCell(3);
        Cell cellBem = row.getCell(4);
        Cell cellParcela = row.getCell(5);
        Cell cellValorBase = row.getCell(6);
        Cell cellComissaoGi3 = row.getCell(7);
        Cell cellComissaoVendedor = row.getCell(8);
        Cell cellDescontoComissao = row.getCell(9);
        Cell cellComissaoLiquida = row.getCell(10);
        Cell cellPg = row.getCell(11);

        repasseBancorbrasDTO.setId(null);
        repasseBancorbrasDTO.setCliente(formatter.formatCellValue(cellCliente));
        repasseBancorbrasDTO.setContrato(formatter.formatCellValue(cellContrato));
        repasseBancorbrasDTO.setVenda(formatter.formatCellValue(cellVenda));
        repasseBancorbrasDTO.setMes(formatter.formatCellValue(cellMes));
        repasseBancorbrasDTO.setBem(formatter.formatCellValue(cellBem));
        repasseBancorbrasDTO.setParcela(formatter.formatCellValue(cellParcela));
        repasseBancorbrasDTO.setValorBase(parseValor(formatter.formatCellValue(cellValorBase)));
        repasseBancorbrasDTO.setComissaoGi3(formatter.formatCellValue(cellComissaoGi3));
        repasseBancorbrasDTO.setComissaoVendedor(parseValor(formatter.formatCellValue(cellComissaoVendedor)));
        repasseBancorbrasDTO.setDescontoComissao(parseValor(formatter.formatCellValue(cellDescontoComissao)));
        repasseBancorbrasDTO.setComissaoLiquida(parseValor(formatter.formatCellValue(cellComissaoLiquida)));
        repasseBancorbrasDTO.setPg(formatter.formatCellValue(cellPg));

        this.saveRepasseBancorbras(repasseBancorbrasDTO);
    }

    private void popularCellHs(Row row) {
        RepasseHsDTO repasseHsDTO = new RepasseHsDTO();
        DataFormatter formatter = new DataFormatter();

        Cell cellCliente = row.getCell(0);
        Cell cellContrato = row.getCell(1);
        Cell cellVenda = row.getCell(2);
        Cell cellMes = row.getCell(3);
        Cell cellBem = row.getCell(4);
        Cell cellParcela = row.getCell(5);
        Cell cellValor_base = row.getCell(6);
        Cell cellComissaoGi3 = row.getCell(7);
        Cell cellComissaoVendedor = row.getCell(8);
        Cell cellPg = row.getCell(9);

        repasseHsDTO.setId(null);
        repasseHsDTO.setCliente(formatter.formatCellValue(cellCliente));
        repasseHsDTO.setContrato(formatter.formatCellValue(cellContrato));
        repasseHsDTO.setVenda(formatter.formatCellValue(cellVenda));
        repasseHsDTO.setMes(formatter.formatCellValue(cellMes));
        repasseHsDTO.setBem(formatter.formatCellValue(cellBem));
        repasseHsDTO.setParcela(formatter.formatCellValue(cellParcela));
        repasseHsDTO.setValorBase(parseValor(formatter.formatCellValue(cellValor_base)));
        repasseHsDTO.setComissaoGi3(parseValor(formatter.formatCellValue(cellComissaoGi3)));
        repasseHsDTO.setComissaoVendedor(parseValor(formatter.formatCellValue(cellComissaoVendedor)));
        repasseHsDTO.setPg(formatter.formatCellValue(cellPg));

        this.saveRepasseHs(repasseHsDTO);
    }

    private void popularCellPrestacaoServico(Row row) {
        PrestacaoServicoDTO prestacaoServicoDTO = new PrestacaoServicoDTO();
        DataFormatter formatter = new DataFormatter();

        Cell cellVendedor = row.getCell(0);
        Cell cellContrato = row.getCell(1);
        Cell cellParcela = row.getCell(2);
        Cell cellValor = row.getCell(3);
        Cell cellEmpresa = row.getCell(4);

        prestacaoServicoDTO.setId(null);
        prestacaoServicoDTO.setVendedor(formatter.formatCellValue(cellVendedor));
        prestacaoServicoDTO.setContrato(formatter.formatCellValue(cellContrato));
        prestacaoServicoDTO.setParcela(formatter.formatCellValue(cellParcela));
        prestacaoServicoDTO.setValor(parseValor(formatter.formatCellValue(cellValor)));
        prestacaoServicoDTO.setEmpresa(formatter.formatCellValue(cellEmpresa));

        this.savePrestacaoServico(prestacaoServicoDTO);
    }

    private void saveRepasseBancorbras(RepasseBancorbrasDTO repasseBancorbrasDTO) {
        RepasseBancorbras repasse = repasseBancorbrasMapper.toEntity(repasseBancorbrasDTO);
        repasseBancorbrasRepository.save(repasse);
        System.out.println(repasseBancorbrasDTO);
    }

    private void saveRepasseHs(RepasseHsDTO repasseHsDTO) {
        RepasseHs repasse = repasseHsMapper.toEntity(repasseHsDTO);
        repasseHsRepository.save(repasse);
        System.out.println(repasseHsDTO);
    }

    private void savePrestacaoServico(PrestacaoServicoDTO prestacaoServicoDTO) {
        PrestacaoServico repasse = prestacaoServicoMapper.toEntity(prestacaoServicoDTO);
        prestacaoServicoRepository.save(repasse);
        System.out.println(prestacaoServicoDTO);
    }

    private String parseValor(String valorFormatado) {
        if (valorFormatado == null || valorFormatado.isBlank()) {
            return "0";
        }
        String valor = valorFormatado
                .replace("R$", "")
                .replace(".", "")
                .replace(",", ".")
                .trim();

        return valor;
    }

}
