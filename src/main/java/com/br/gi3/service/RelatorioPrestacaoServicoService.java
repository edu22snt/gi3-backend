package com.br.gi3.service;

import com.br.gi3.service.dto.PrestacaoServicoDTO;
import com.br.gi3.service.dto.RepasseBancorbrasDTO;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class RelatorioPrestacaoServicoService {

    public ByteArrayInputStream gerarRelatorio(List<PrestacaoServicoDTO> lista, String filtro) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titulo = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font normal = new Font(Font.HELVETICA, 10);

            document.add(new Paragraph("Relatório de Prestação de Serviço", titulo));
            document.add(new Paragraph("Filtro: " + filtro, normal));
            document.add(new Paragraph("Data: " + LocalDate.now(), normal));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);

            Stream.of("ID", "Vendedor", "Contrato", "Parcela", "Valor", "Empresa")
                    .forEach(header -> {
                        PdfPCell cell = new PdfPCell(new Phrase(header));
                        cell.setBackgroundColor(Color.LIGHT_GRAY);
                        table.addCell(cell);
                    });

            for (PrestacaoServicoDTO item : lista) {
                table.addCell(String.valueOf(item.getId()));
                table.addCell(item.getVendedor());
                table.addCell(item.getContrato());
                table.addCell(item.getParcela());
                table.addCell(String.valueOf(item.getValor()));
                table.addCell(item.getEmpresa());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream gerarRelatorioBancorbras(List<RepasseBancorbrasDTO> lista, String filtro) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

//        long valor = lista.stream()
//                .mapToLong(item -> item.getComissaoLiquida() == null ? 0L : Long.parseLong(item.getComissaoLiquida()))
//                .sum();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titulo = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font normal = new Font(Font.HELVETICA, 10);

            document.add(new Paragraph("Relatório de Bancorbrás", titulo));
            document.add(new Paragraph("Filtro: " + filtro, normal));
            document.add(new Paragraph("Data: " + LocalDate.now(), normal));
            document.add(new Paragraph(""));
//            document.add(new Paragraph("Comissão Líquida " + valor));

            PdfPTable table = new PdfPTable(10);
            table.setWidthPercentage(100);

            Stream.of("Cliente", "Contrato", "Venda", "Mês", "Bem", "Parcela", "Valor Base", "Comissão Vendedor", "Desconto Comissão", "Comissão Líquida")
                    .forEach(header -> {
                        PdfPCell cell = new PdfPCell(new Phrase(header));
                        cell.setBackgroundColor(Color.LIGHT_GRAY);
                        table.addCell(cell);
                    });

            for (RepasseBancorbrasDTO item : lista) {
                table.addCell(item.getCliente());
                table.addCell(item.getContrato());
                table.addCell(item.getVenda());
                table.addCell(item.getMes());
                table.addCell(item.getBem());
                table.addCell(item.getParcela());
                table.addCell(item.getValorBase());
                table.addCell(item.getComissaoVendedor());
                table.addCell(item.getDescontoComissao());
                table.addCell(item.getComissaoLiquida());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
