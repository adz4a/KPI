package com.program.service.reportGenerate;

import com.program.exception.DepartmentException;
import com.program.helper.report.ExportType;
import com.program.model.Department;
import net.sf.jasperreports.engine.JRException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ReportGenerationService {

    void getByDepartment(Integer id, ExportType exportType, HttpServletResponse response)throws DepartmentException, JRException, IOException;
}
