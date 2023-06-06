package com.program.controller;


import com.program.exception.CategoryException;
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



@RestController
public class ReportController {


    @Autowired
    public TeacherEventService teacherEventService;

    @Autowired
    public ReportGenerationService reportGenerationService;


    @GetMapping(value = "report/department/{id}")
    ResponseEntity<Void> reportByDepartment(@RequestParam(value = "exportType") ExportType exportType,
                                                        HttpServletResponse response, @PathVariable("id") Integer id) throws IOException, JRException, DepartmentException {
        reportGenerationService.getByDepartment(id,exportType,response);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "report/category/{id}")
    ResponseEntity<Void> reportByCategory(@RequestParam(value = "exportType") ExportType exportType,
                                                        HttpServletResponse response, @PathVariable("id") Integer id) throws IOException, JRException, CategoryException {
        reportGenerationService.getByCategory(id,exportType,response);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "report/category/{categoryId}/status/{statusId}")
    ResponseEntity<Void> reportByCategoryAndStatus(@RequestParam(value = "exportType") ExportType exportType,
                                          HttpServletResponse response, @PathVariable("categoryId") Integer categoryId,@PathVariable("statusId") Integer statusId)  throws IOException, JRException, CategoryException {
        reportGenerationService.getByCategoryAndStatus(categoryId,statusId, exportType,response);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "report/department/{departmentId}/category/{categoryId}/status/{statusId}")
    ResponseEntity<Void> reportByDepartmentAndCategoryAndStatus(@RequestParam(value = "exportType") ExportType exportType,
                                                   HttpServletResponse response,@PathVariable("departmentId") Integer departmentId, @PathVariable("categoryId") Integer categoryId,@PathVariable("statusId") Integer statusId)  throws IOException, JRException{
        reportGenerationService.getByDepartmentAndCategoryAndStatus(departmentId,categoryId,statusId, exportType,response);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "report/department/{departmentId}/category/{categoryId}")
    ResponseEntity<Void> reportByDepartmentAndCategory(@RequestParam(value = "exportType") ExportType exportType,
                                                                HttpServletResponse response,@PathVariable("departmentId") Integer departmentId, @PathVariable("categoryId") Integer categoryId)  throws IOException, JRException {
        reportGenerationService.getByDepartmentAndCategory(departmentId,categoryId, exportType,response);
        return ResponseEntity.ok().build();
    }

}
