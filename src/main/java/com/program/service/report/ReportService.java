package com.program.service.report;



import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

//    private final TransactionService transactionService;
//
//    public ReportService(TransactionService transactionService) {
//        this.transactionService = transactionService;
//    }
//
//    public void downloadTransactionReport(ExportType exportType, HttpServletResponse response) throws JRException, IOException {
//        List<Transaction> transactionList = transactionService.getTransactionList();
//        exportReport(transactionList, exportType, response);
//    }

//    private void exportReport(Collection<?> beanCollection, ExportType exportType, HttpServletResponse response) throws JRException, IOException {
//        InputStream transactionReportStream =
//                getClass()
//                        .getResourceAsStream(
//                                "/report_" + exportType.toString().toLowerCase() + ".jrxml");
//        String titleTransactionBy = "Report";
//
//        JasperReport jasperReport = JasperCompileManager.compileReport(transactionReportStream);
//        JRBeanCollectionDataSource beanColDataSource =
//                new JRBeanCollectionDataSource(beanCollection);
//        HashMap<String, Object> parameters = new HashMap();
//        parameters.put("title", titleTransactionBy);
//
//        JasperPrint jasperPrint =
//                JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        var dateTimeNow = LocalDateTime.now().format(formatter);
//        var fileName = titleTransactionBy.replace(" ", "") + dateTimeNow;
//
//        if (exportType == ExportType.PDF) {
//
//            JRPdfExporter exporter = new JRPdfExporter();
//            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
//            response.setContentType("application/pdf");
//            response.setHeader(
//                    HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".pdf;");
//            exporter.exportReport();
//
//        } else if (exportType == ExportType.EXCEL) {
//
//            JRXlsxExporter exporter = new JRXlsxExporter();
//            SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
//            reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
//            reportConfigXLS.setDetectCellType(true);
//            reportConfigXLS.setCollapseRowSpan(false);
//            exporter.setConfiguration(reportConfigXLS);
//            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
//            response.setHeader(
//                    HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".xlsx;");
//            response.setContentType("application/octet-stream");
//            exporter.exportReport();
//        } else {
//            throw new RuntimeException("File Format isn't supported!");
//        }
//    }
}
