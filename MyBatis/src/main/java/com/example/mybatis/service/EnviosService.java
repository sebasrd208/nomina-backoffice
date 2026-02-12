package com.example.mybatis.service;

import com.example.mybatis.DTO.CompaniaDTO;
import com.example.mybatis.DTO.DeduccionesDTO;
import java.util.List;
import com.example.mybatis.DTO.DocumentoDTO;
import com.example.mybatis.DTO.ImpuestosDTO;
import com.example.mybatis.DTO.SueldoNetoDTO;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.mail.internet.MimeMessage;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * @author sebas
 */

@Service
public class EnviosService {
    
    @Autowired
    DocumentoService servicio;
    
    @Autowired
    EmpleadoService servicioE;
    
    @Autowired
    JavaMailSender envioCorreo;
    
    public byte[] generatePdfSueldo(String nombre) throws Exception {

        SueldoNetoDTO dto = servicioE.obtenerSueldoNeto(nombre);

        if (dto == null || dto.getDatos() == null || dto.getDatos().isEmpty()) {
            return null; // o lanzar una excepción según tu lógica
        }

        ByteArrayOutputStream out;
        try (Document document = new Document(PageSize.LETTER)) {
            
            out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);
            document.setMargins(40, 40, 40, 40);
            document.open();
            
            Font tituloFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font headerFont = new Font(Font.HELVETICA, 11, Font.BOLD, Color.BLACK);
            Font boldFont = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font smallFont = new Font(Font.HELVETICA, 10, Font.NORMAL);
            
            Color lightGray = new Color(230, 230, 230);
            CompaniaDTO datos = dto.getDatos().get(0);
            DeduccionesDTO ded = dto.getDeducciones().get(0);
            ImpuestosDTO imp = dto.getImpuestos().get(0);
            
            ClassPathResource classPathResource = new ClassPathResource("crunchyroll-logo.png");
            try (InputStream is = classPathResource.getInputStream()) {
                byte[] bytes = is.readAllBytes();
                Image logo = Image.getInstance(bytes);
                logo.scaleToFit(150, 75);
                float x = PageSize.LETTER.getWidth() - 40 - logo.getScaledWidth();
                float y = PageSize.LETTER.getHeight() - 40 - logo.getScaledHeight();
                logo.setAbsolutePosition(x, y);
                document.add(logo);
            }
            
            int mes = Integer.parseInt(datos.getTrimestre());
            String periodo;
            
            if (mes <= 3) {
                periodo = "ENERO - MARZO";
            } else if (mes <= 6) {
                periodo = "ABRIL - JUNIO";
            } else if (mes <= 9) {
                periodo = "JULIO - SEPTIEMBRE";
            } else {
                periodo = "OCTUBRE - DICIEMBRE";
            }
            
            String periodoCompleto = periodo;
            
            Paragraph titulo = new Paragraph("MI RECIBO TRIMESTRAL", tituloFont);
            titulo.setAlignment(Element.ALIGN_LEFT);
            document.add(titulo);
            
            Paragraph periodoPdf = new Paragraph("PERIODO " + periodoCompleto, boldFont);
            periodoPdf.setAlignment(Element.ALIGN_LEFT);
            document.add(periodoPdf);
            
            document.add(new Paragraph(" "));
            
            Paragraph nombreCompleto = new Paragraph(datos.getNombre().toUpperCase() + " " + datos.getApellido().toUpperCase(), boldFont);
            nombreCompleto.setAlignment(Element.ALIGN_LEFT);
            document.add(nombreCompleto);
            
            Paragraph num_empleado = new Paragraph("No. Empleado: " + datos.getNum_empleado(), smallFont);
            num_empleado.setAlignment(Element.ALIGN_LEFT);
            document.add(num_empleado);
            
            Paragraph rfc = new Paragraph("RFC: " + datos.getRfc(), smallFont);
            rfc.setAlignment(Element.ALIGN_LEFT);
            document.add(rfc);
            
            Paragraph compania = new Paragraph("Compañia: " + datos.getCompania(), smallFont);
            compania.setAlignment(Element.ALIGN_LEFT);
            document.add(compania);
            
            document.add(new Paragraph(" "));
            
            Paragraph nota = new Paragraph(datos.getNota(), smallFont);
            nota.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(nota);
            
            document.add(new Paragraph(" "));
            
            // Tabla de deducciones
            PdfPTable tablaDeducciones = new PdfPTable(new float[]{3, 2});
            tablaDeducciones.setWidthPercentage(95);
            tablaDeducciones.setSpacingAfter(15f);
            
            PdfPCell hd1 = new PdfPCell(new Phrase("CONCEPTO", headerFont));
            PdfPCell hd2 = new PdfPCell(new Phrase("IMPORTE", headerFont));
            
            hd1.setBackgroundColor(Color.GRAY);
            hd2.setBackgroundColor(Color.GRAY);
            
            hd1.setHorizontalAlignment(Element.ALIGN_CENTER);
            hd2.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            hd1.setPadding(5);
            hd2.setPadding(5);
            
            tablaDeducciones.addCell(hd1);
            tablaDeducciones.addCell(hd2);
            
            String[][] filasDeducciones = {
                {"Importe Total Trabajo", ded.getBruto()},
                {"ISR", ded.getISR()},
                {"IMSS", ded.getIMSS()},
                {"Fondo Retiro", ded.getFondo()},
                {"Neto Pagado al Empleado", ded.getNeto()}
            };
            
            PdfPCell separator = new PdfPCell();
            separator.setColspan(2);
            separator.setBorder(Rectangle.BOTTOM);
            separator.setBorderWidth(2);
            separator.setFixedHeight(3f);
            
            tablaDeducciones.addCell(separator);
            
            for (int i = 0; i < filasDeducciones.length; i++) {
                
                PdfPCell c1 = new PdfPCell(new Phrase(filasDeducciones[i][0]));
                PdfPCell c2 = new PdfPCell(new Phrase("$ " + filasDeducciones[i][1]));
                
                c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                
                if (i % 2 == 0) {
                    c1.setBackgroundColor(lightGray);
                    c2.setBackgroundColor(lightGray);
                }
                
                c1.setPadding(6);
                c2.setPadding(6);
                
                tablaDeducciones.addCell(c1);
                tablaDeducciones.addCell(c2);
                
            }
            document.add(tablaDeducciones);
            
            // Tabla de impuestos
            PdfPTable tablaImpuestos = new PdfPTable(new float[]{3, 2});
            
            tablaImpuestos.setWidthPercentage(95);
            tablaImpuestos.setSpacingAfter(15f);
            
            PdfPCell hi1 = new PdfPCell(new Phrase("CONCEPTO", headerFont));
            PdfPCell hi2 = new PdfPCell(new Phrase("IMPORTE", headerFont));
            
            hi1.setBackgroundColor(Color.RED);
            hi2.setBackgroundColor(Color.RED);
            
            hi1.setHorizontalAlignment(Element.ALIGN_CENTER);
            hi2.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            hi1.setPadding(5);
            hi2.setPadding(5);
            
            tablaImpuestos.addCell(hi1);
            tablaImpuestos.addCell(hi2);
            
            String[][] filasImpuestos = {
                {"Total Pagado al Gobierno", imp.getImpuesto()},
                {"ISR", imp.getISR()},
                {"IMSS", imp.getIMSS()}
            };
            
            PdfPCell separator2 = new PdfPCell();
            separator2.setColspan(2);
            separator2.setBorder(Rectangle.BOTTOM);
            separator2.setBorderWidth(2);
            separator2.setFixedHeight(3f);
            tablaImpuestos.addCell(separator2);
            
            for (int i = 0; i < filasImpuestos.length; i++) {
                PdfPCell c1 = new PdfPCell(new Phrase(filasImpuestos[i][0]));
                PdfPCell c2 = new PdfPCell(new Phrase("$ " + filasImpuestos[i][1]));
                
                c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                
                if (i % 2 == 0) {
                    c1.setBackgroundColor(lightGray);
                    c2.setBackgroundColor(lightGray);
                }
                
                c1.setPadding(6);
                c2.setPadding(6);
                
                tablaImpuestos.addCell(c1);
                tablaImpuestos.addCell(c2);
            }
            
            document.add(tablaImpuestos);
            
        }

        return out.toByteArray();
    }
    
    @Async
    public void envioCorreoAdjunto(String correo, String nombre) {

        try {
            byte[] pdfBytes = generatePdfSueldo(nombre);

            /*if (pdfBytes == null || pdfBytes.length == 0) {
                throw new IllegalArgumentException("No se pudo generar el PDF para: " + nombre);
            }*/

            MimeMessage message = envioCorreo.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("sebastianramiro175@gmail.com");
            helper.setTo(correo);
            helper.setSubject("Prueba de la API de correo con archivos adjuntos");
            helper.setText("A continuación encontrará el documento adjunto.");

            helper.addAttachment("Recibo_" + nombre + ".pdf", new ByteArrayResource(pdfBytes));

            guardarPdfEnBD(nombre);

            envioCorreo.send(message);
        } catch (Exception  s) {
            throw new IllegalArgumentException("No se pudo generar el PDF para: " + nombre);
        }
    }

    public String generarPdfBase64(String nombre) throws Exception {
        byte[] pdfBytes = generatePdfSueldo(nombre);
        return Base64.getEncoder().encodeToString(pdfBytes);
    }

    public void guardarPdfEnBD(String nombre) {
        try {
            String pdfBase64 = generarPdfBase64(nombre);
            DocumentoDTO dto = new DocumentoDTO();
            dto.setNombre(nombre);
            dto.setDocumento(pdfBase64);
            dto.setStatus("1");

            servicio.actualizarDocumenctos(dto);
        } catch (Exception e) {
            DocumentoDTO dto = new DocumentoDTO();
            dto.setNombre(nombre);
            dto.setDocumento(null);
            dto.setStatus("2");
            servicio.actualizarDocumenctos(dto);
        }
    }

    public void procesarEnvioCorreos() {        
        List<DocumentoDTO> documentosPendientes = servicio.obtenerStatus();

        for (DocumentoDTO doc : documentosPendientes) {            
            envioCorreoAdjunto(doc.getCorreo(), doc.getNombre());            
        }
    }

}
