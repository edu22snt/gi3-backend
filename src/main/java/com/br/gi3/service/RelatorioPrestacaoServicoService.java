package com.br.gi3.service;

import com.br.gi3.service.dto.PrestacaoServicoDTO;
import com.br.gi3.service.dto.RepasseBancorbrasDTO;
import com.br.gi3.service.dto.RepasseHsDTO;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class RelatorioPrestacaoServicoService {

    public ByteArrayInputStream gerarRelatorioPrestacaoServico(List<PrestacaoServicoDTO> lista, String filtro) {

        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        BigDecimal total = BigDecimal.ZERO;

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titulo = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font normal = new Font(Font.HELVETICA, 10);
            Font headerFont = new Font(Font.HELVETICA, 10, Font.BOLD);

            Paragraph tituloP = new Paragraph("GI3 - Relatório de Prestação de Serviço", titulo);
            tituloP.setAlignment(Element.ALIGN_CENTER);
            document.add(tituloP);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            document.add(new Paragraph("Filtro: " + filtro, normal));
            document.add(new Paragraph("Data: " + LocalDate.now().format(formatter), normal));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            table.setWidths(new float[]{
                    3, // Vendedor
                    2, // Contrato
                    2, // Parcela
                    2, // Valor
                    2, // Empresa
            });

            Stream.of("Vendedor", "Contrato", "Parcela", "Valor", "Empresa")
                    .forEach(header -> {
                        PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                        cell.setBackgroundColor(Color.LIGHT_GRAY);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    });

            for (PrestacaoServicoDTO item : lista) {
                BigDecimal valorBase = tratarValor(item.getValor());

                table.addCell(item.getVendedor());
                table.addCell(item.getContrato());
                table.addCell(item.getParcela());
                table.addCell(criarCelulaMoeda(valorBase));
                table.addCell(item.getEmpresa());

                total = total.add(valorBase);
            }

            PdfPCell totalLabel = new PdfPCell(new Phrase("TOTAL", headerFont));
            totalLabel.setColspan(4);
            totalLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalLabel.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(totalLabel);

            PdfPCell totalValor = criarCelulaMoeda(total);
            totalValor.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(totalValor);

            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream gerarRelatorioBancorbras(List<RepasseBancorbrasDTO> lista, String filtro) {

        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        BigDecimal total = BigDecimal.ZERO;

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titulo = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font normal = new Font(Font.HELVETICA, 10);
            Font headerFont = new Font(Font.HELVETICA, 10, Font.BOLD);

            Paragraph tituloP = new Paragraph("GI3 - Relatório Repasse Bancorbrás", titulo);
            tituloP.setAlignment(Element.ALIGN_CENTER);
            document.add(tituloP);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            document.add(new Paragraph("Filtro: " + filtro, normal));
            document.add(new Paragraph("Data: " + LocalDate.now().format(formatter), normal));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(10);
            table.setWidthPercentage(100);

            table.setWidths(new float[]{
                    3, // Cliente
                    2, // Contrato
                    2, // Venda
                    2, // Mês
                    2, // Bem
                    2, // Parcela
                    3, // Valor Base
                    3, // Comissão Vendedor
                    3, // Desconto
                    3  // Comissão Líquida
            });

            Stream.of("Cliente", "Contrato", "Venda", "Mês", "Bem", "Parcela",
                    "Valor Base", "Comissão Vendedor", "Desconto", "Comissão Líquida")
                    .forEach(header -> {
                        PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                        cell.setBackgroundColor(Color.LIGHT_GRAY);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    });

            for (RepasseBancorbrasDTO item : lista) {

                table.addCell(item.getCliente());
                table.addCell(item.getContrato());
                table.addCell(item.getVenda());
                table.addCell(item.getMes());
                table.addCell(item.getBem());
                table.addCell(item.getParcela());

                BigDecimal valorBase = tratarValor(item.getValorBase());
                BigDecimal comissaoVend = tratarValor(item.getComissaoVendedor());
                BigDecimal desconto = tratarValor(item.getDescontoComissao());
                BigDecimal comissao = tratarValor(item.getComissaoLiquida());

                table.addCell(criarCelulaMoeda(valorBase));
                table.addCell(criarCelulaMoeda(comissaoVend));
                table.addCell(criarCelulaMoeda(desconto));
                table.addCell(criarCelulaMoeda(comissao));

                total = total.add(comissao);
            }

            PdfPCell totalLabel = new PdfPCell(new Phrase("TOTAL", headerFont));
            totalLabel.setColspan(9);
            totalLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalLabel.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(totalLabel);

            PdfPCell totalValor = criarCelulaMoeda(total);
            totalValor.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(totalValor);

            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream gerarRelatorioHs(List<RepasseHsDTO> lista, String filtro) {

        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        BigDecimal total = BigDecimal.ZERO;

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titulo = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font normal = new Font(Font.HELVETICA, 10);
            Font headerFont = new Font(Font.HELVETICA, 10, Font.BOLD);

            Paragraph tituloP = new Paragraph("GI3 - Relatório Repasse HS", titulo);
            tituloP.setAlignment(Element.ALIGN_CENTER);
            document.add(tituloP);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            document.add(new Paragraph("Filtro: " + filtro, normal));
            document.add(new Paragraph("Data: " + LocalDate.now().format(formatter), normal));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);

            table.setWidths(new float[]{
                    3, // Cliente
                    2, // Contrato
                    2, // Venda
                    2, // Mês
                    2, // Bem
                    2, // Parcela
                    3, // Valor Base
                    3, // Comissão Vendedor
            });

            Stream.of("Cliente", "Contrato", "Venda", "Mês", "Bem", "Parcela",
                    "Valor Base", "Comissão Vendedor")
                    .forEach(header -> {
                        PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                        cell.setBackgroundColor(Color.LIGHT_GRAY);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    });

            for (RepasseHsDTO item : lista) {

                table.addCell(item.getCliente());
                table.addCell(item.getContrato());
                table.addCell(item.getVenda());
                table.addCell(item.getMes());
                table.addCell(item.getBem());
                table.addCell(item.getParcela());

                BigDecimal valorBase = tratarValor(item.getValorBase());
                BigDecimal comissaoVend = tratarValor(item.getComissaoVendedor());

                table.addCell(criarCelulaMoeda(valorBase));
                table.addCell(criarCelulaMoeda(comissaoVend));

                total = total.add(comissaoVend);
            }

            PdfPCell totalLabel = new PdfPCell(new Phrase("TOTAL", headerFont));
            totalLabel.setColspan(7);
            totalLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalLabel.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(totalLabel);

            PdfPCell totalValor = criarCelulaMoeda(total);
            totalValor.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(totalValor);

            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private PdfPCell criarCelulaMoeda(BigDecimal valor) {
        PdfPCell cell = new PdfPCell(new Phrase(formatarMoeda(valor)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setNoWrap(true);
        return cell;
    }

    private String formatarMoeda(BigDecimal valor) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(valor);
    }

    private BigDecimal tratarValor(String valor) {
        if (valor == null || valor.isBlank()) {
            return BigDecimal.ZERO;
        }
        valor = valor.replaceAll("[^0-9,.]", "");
        valor = valor.replace(",", ".");
        return new BigDecimal(valor);
    }
}
