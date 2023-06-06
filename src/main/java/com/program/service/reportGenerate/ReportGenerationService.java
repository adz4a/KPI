package com.program.service.reportGenerate;

import com.program.exception.CategoryException;
import com.program.exception.DepartmentException;
import com.program.helper.report.ExportType;
import com.program.model.Category;
import com.program.model.Department;
import net.sf.jasperreports.engine.JRException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ReportGenerationService {

    void getByDepartment(Integer id, ExportType exportType, HttpServletResponse response)throws DepartmentException, JRException, IOException;

    void getByCategory(Integer id,ExportType exportType, HttpServletResponse response)throws CategoryException, JRException, IOException;

    void getByCategoryAndStatus(Integer categoryId, Integer statusId, ExportType exportType, HttpServletResponse response)throws JRException, IOException;

    void getByDepartmentAndCategoryAndStatus(Integer departmentId,Integer categoryId, Integer statusId, ExportType exportType, HttpServletResponse response)throws JRException, IOException;

    void getByDepartmentAndCategory(Integer departmentId, Integer categoryId, ExportType exportType, HttpServletResponse response)throws JRException, IOException;

}
