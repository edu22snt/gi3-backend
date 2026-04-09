package com.br.gi3.service;

import com.br.gi3.enumerate.TipoPlanilhaEnum;
import com.br.gi3.model.RepasseBancorbras;
import com.br.gi3.model.RepasseHs;
import com.br.gi3.repository.RepasseBancorbrasRepository;
import com.br.gi3.repository.RepasseHsRepository;
import com.br.gi3.service.dto.RepasseBancorbrasDTO;
import com.br.gi3.service.dto.RepasseHsDTO;
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

@Service
@Transactional
public class UploadService {

    private final Logger log = LoggerFactory.getLogger(UploadService.class);
    private RepasseBancorbrasRepository repasseBancorbrasRepository;
    private RepasseBancorbrasMapper repasseBancorbrasMapper;
    private RepasseHsRepository repasseHsRepository;
    private RepasseHsMapper repasseHsMapper;

    public UploadService(
            RepasseBancorbrasRepository repasseBancorbrasRepository,
            RepasseBancorbrasMapper repasseBancorbrasMapper,
            RepasseHsRepository repasseHsRepository,
            RepasseHsMapper repasseHsMapper
    ) {
        this.repasseBancorbrasRepository = repasseBancorbrasRepository;
        this.repasseBancorbrasMapper = repasseBancorbrasMapper;
        this.repasseHsRepository = repasseHsRepository;
        this.repasseHsMapper = repasseHsMapper;

    }

    public void importFileBancorbras(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        if (filename.endsWith(".csv")) {
            readCSV(file);
        } else if (filename.endsWith(".xlsx") || filename.endsWith(".xls")) {
            readExcel(file, TipoPlanilhaEnum.BANCORBRAS);
        } else {
            throw new RuntimeException("Formato de arquivo não suportado");
        }
    }

    public void importFileHs(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        if (filename.endsWith(".csv")) {
            readCSV(file);
        } else if (filename.endsWith(".xlsx") || filename.endsWith(".xls")) {
            readExcel(file, TipoPlanilhaEnum.HS);
        } else {
            throw new RuntimeException("Formato de arquivo não suportado");
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
        Cell cellValor_base = row.getCell(6);
        Cell cellComissao_gi3 = row.getCell(7);
        Cell cellComissao_vendedor = row.getCell(8);
        Cell cellDesconto_comissao = row.getCell(9);
        Cell cellComissao_liquida = row.getCell(10);
        Cell cellPg = row.getCell(11);

        repasseBancorbrasDTO.setId(null);
        repasseBancorbrasDTO.setCliente(formatter.formatCellValue(cellCliente));
        repasseBancorbrasDTO.setContrato(formatter.formatCellValue(cellContrato));
        repasseBancorbrasDTO.setVenda(formatter.formatCellValue(cellVenda));
        repasseBancorbrasDTO.setMes(formatter.formatCellValue(cellMes));
        repasseBancorbrasDTO.setBem(formatter.formatCellValue(cellBem));
        repasseBancorbrasDTO.setParcela(formatter.formatCellValue(cellParcela));
        repasseBancorbrasDTO.setValorBase(formatter.formatCellValue(cellValor_base));
        repasseBancorbrasDTO.setComissaoGi3(formatter.formatCellValue(cellComissao_gi3));
        repasseBancorbrasDTO.setComissaoVendedor(formatter.formatCellValue(cellComissao_vendedor));
        repasseBancorbrasDTO.setDescontoComissao(formatter.formatCellValue(cellDesconto_comissao));
        repasseBancorbrasDTO.setComissaoLiquida(formatter.formatCellValue(cellComissao_liquida));
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
        Cell cellComissao_gi3 = row.getCell(7);
        Cell cellComissao_vendedor = row.getCell(8);
        Cell cellPg = row.getCell(9);

        repasseHsDTO.setId(null);
        repasseHsDTO.setCliente(formatter.formatCellValue(cellCliente));
        repasseHsDTO.setContrato(formatter.formatCellValue(cellContrato));
        repasseHsDTO.setVenda(formatter.formatCellValue(cellVenda));
        repasseHsDTO.setMes(formatter.formatCellValue(cellMes));
        repasseHsDTO.setBem(formatter.formatCellValue(cellBem));
        repasseHsDTO.setParcela(formatter.formatCellValue(cellParcela));
        repasseHsDTO.setValorBase(formatter.formatCellValue(cellValor_base));
        repasseHsDTO.setComissao_gi3(formatter.formatCellValue(cellComissao_gi3));
        repasseHsDTO.setComissao_vendedor(formatter.formatCellValue(cellComissao_vendedor));
        repasseHsDTO.setPg(formatter.formatCellValue(cellPg));

        this.saveRepasseHs(repasseHsDTO);
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

}
