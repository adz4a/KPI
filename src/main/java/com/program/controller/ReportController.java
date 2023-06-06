package com.program.controller;


import com.program.exception.DepartmentException;
import com.program.helper.report.ExportType;
import com.program.service.TeacherEventService;
import com.program.service.reportGenerate.ReportGenerationService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static java.lang.String.format;


@RestController
public class ReportController {


    @Autowired
    public TeacherEventService teacherEventService;

    @Autowired
    public ReportGenerationService reportGenerationService;


    @GetMapping(value = "report/department/{id}")
    ResponseEntity<Void> exportDynamicTransactionReport(@RequestParam(value = "exportType") ExportType exportType,
                                                        HttpServletResponse response, @PathVariable("id") Integer id) throws IOException, JRException, DepartmentException {
        reportGenerationService.getByDepartment(id,exportType,response);
        return ResponseEntity.ok().build();
    }


}
