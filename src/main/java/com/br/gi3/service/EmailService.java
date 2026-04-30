package com.br.gi3.service;

import com.br.gi3.model.Contrato;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void enviarContratoCriado(Contrato contrato) {

        LocalDateTime agora = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = agora.format(formatter);

        try {

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(contrato.getVendedor().getEmail());
            helper.setSubject("Novo contrato criado");

            String html = """
                    <h2> Novo contrato cadastrado</h2>

                    <p><b>Contrato:</b> %s</p>
                    <p><b>Vendedor:</b> %s</p>
                    <p><b>Tipo do bem:</b> %s</p>
                    <p><b>Empresa:</b> %s</p>
                    <p><b>Quantidade de parcelas:</b> %s</p>
                    <p><b>Valor: </b> %s</p>
                    <p><b>Data: </b> %s</p>
                    """
                    .formatted(
                            contrato.getNumeroContrato(),
                            contrato.getVendedor().getNome(),
                            contrato.getTipo(),
                            contrato.getEmpresa(),
                            contrato.getQntParcelas(),
                            formatCurrency(contrato.getValor()),
                            formatterDate()
                    );

            helper.setText(html, true);

            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatterDate() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return agora.format(formatter);
    }

    private String formatCurrency(BigDecimal valor) {
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(ptBr);
        return formatter.format(valor);
    }
}
