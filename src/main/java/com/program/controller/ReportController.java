package com.program.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.program.exception.ReportException;
import com.program.exception.SubmissionException;
import com.program.exception.TeacherEventException;
import com.program.exception.UserException;
import com.program.model.teacher.TeacherEvent;
import com.program.service.ReportService;
import com.program.service.TeacherEventService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;


@RestController
public class ReportController {

    @Autowired
    public ReportService reportService;

    @Autowired
    public TeacherEventService teacherEventService;

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity generatePdf() {
        try {
            byte[] pdfBytes = reportService.generatePdf();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        }catch (IOException | ReportException ex) {
            String errorMessage = "Error setting: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/generatePDF")
    public void generatePDF(HttpServletResponse response) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Load image
//                PDImageXObject pdImage = PDImageXObject.createFromFile(new ClassPathResource("aitu.png").getURL().getPath(), document);

                // Draw the image at a position with a certain width and height
//                contentStream.drawImage(pdImage, 20, 700, 100, 100);

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(150, 750);
                contentStream.showText("Report on (date): " + LocalDateTime.now());
                contentStream.endText();

                // Add more text, tables, etc.

                contentStream.close();

                // Set the content type and download
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=Report.pdf");
                document.save(response.getOutputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addTableHeader(PdfPTable table, List<String> headers) {
        headers.forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }

    private void addRow(PdfPTable table, List<String> columns) {
        columns.forEach(column -> table.addCell(column));
    }


}
