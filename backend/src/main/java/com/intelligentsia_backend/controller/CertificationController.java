package com.intelligentsia_backend.controller;

import com.intelligentsia_backend.services.service.CertificationService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
@Controller
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @Autowired
    private TemplateEngine templateEngine;
    //@PostAuthorize("hasAuthority('USER')")
    @GetMapping("/certification")
    public ResponseEntity<ByteArrayResource> generateCertificationPdf() throws IOException, DocumentException, com.lowagie.text.DocumentException {
        // Get certification data from database
        //Certification certification = certificationService.findById(id).orElseThrow(() -> new RuntimeException("Certification not found"));

        // Prepare Thymeleaf context with certification data
        Context context = new Context();
      /*  context.setVariable("student", certification.getStudent());
        context.setVariable("course", certification.getCourse());
        context.setVariable("date", certification.getDate());*/

        // Render HTML template with Thymeleaf
        String html = templateEngine.process("certificat", context);

        // Create PDF document
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, out);

        // Convert HTML to PDF with iText and Flying Saucer
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(out); // Use the ByteArrayOutputStream as output

        // Prepare HTTP response with PDF file
        ByteArrayResource resource = new ByteArrayResource(out.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=certification.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        return ResponseEntity.ok().headers(headers).body(resource);
    }

}
