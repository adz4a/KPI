package com.program.service.reportGenerate;

import com.program.exception.DepartmentException;
import com.program.helper.report.ExportType;
import com.program.model.Department;
import com.program.model.teacher.Teacher;
import com.program.model.teacher.TeacherEvent;
import com.program.payload.response.ReportResponse;
import com.program.repository.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportGenerationServiceImpl implements ReportGenerationService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherEventRepository teacherEventRepository;

    @Autowired
    private ApproveRepository approveRepository;




    @Override
    public void getByDepartment(Integer id,ExportType exportType, HttpServletResponse response) throws DepartmentException,JRException, IOException {
        Department department = departmentRepository.findByDepartmentId(id);
        List<ReportResponse> reportResponseList = new ArrayList<>();
        if (department!=null) {
            List<Teacher> teacherList = teacherRepository.findByDepartment(department.getDepartmentId());
                for (Teacher teacher : teacherList) {
                List<TeacherEvent> teacherEventList = teacherEventRepository.findEventsByTeacherId(teacher.getTeacherId());
                int rejectedEvents = 0;
                int acceptedEvents = 0;
                int leftEvents = 0;
                for (TeacherEvent teacherEvent:teacherEventList){
                    if (teacherEvent.isAccept()){
                        rejectedEvents++;
                    }
                    if (teacherEvent.isReject()){
                        rejectedEvents++;
                    }
                    if (teacherEvent.isNone()){
                        leftEvents++;
                    }
                }
                    ReportResponse newReportResponse = new ReportResponse(teacher.getUserName(), teacher.getUserEmail(), teacher.getCategoryName(), teacher.getStatusName(), teacher.getDepartmentName(), acceptedEvents, rejectedEvents, leftEvents);
                    reportResponseList.add(newReportResponse);
                }
            exportReport(reportResponseList,exportType,response);
        }

        }


    private void exportReport(Collection<?> beanCollection, ExportType exportType, HttpServletResponse response) throws JRException, IOException {
        InputStream transactionReportStream =
                getClass()
                        .getResourceAsStream(
                                "/report_" + exportType.toString().toLowerCase() + ".jrxml");
        String titleTransactionBy = "Report";

        JasperReport jasperReport = JasperCompileManager.compileReport(transactionReportStream);
        JRBeanCollectionDataSource beanColDataSource =
                new JRBeanCollectionDataSource(beanCollection);
        HashMap<String, Object> parameters = new HashMap();
        parameters.put("title", titleTransactionBy);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        var dateTimeNow = LocalDateTime.now().format(formatter);
        var fileName = titleTransactionBy.replace(" ", "") + dateTimeNow;

        if (exportType == ExportType.PDF) {

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            response.setContentType("application/pdf");
            response.setHeader(
                    HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".pdf;");
            exporter.exportReport();

        } else if (exportType == ExportType.EXCEL) {

            JRXlsxExporter exporter = new JRXlsxExporter();
            SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
            reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
            reportConfigXLS.setDetectCellType(true);
            reportConfigXLS.setCollapseRowSpan(false);
            exporter.setConfiguration(reportConfigXLS);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            response.setHeader(
                    HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".xlsx;");
            response.setContentType("application/octet-stream");
            exporter.exportReport();
        } else {
            throw new RuntimeException("File Format isn't supported!");
        }
    }
}
