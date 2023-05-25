package com.program.controller;

import com.program.exception.DepartmentException;
import com.program.model.Department;
import com.program.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    public DepartmentService departmentService;

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllTeachers() throws DepartmentException {
        List<Department> departments = departmentService.findAllDepartments();
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
    }

    @GetMapping("/department/getById/{Id}")
    public ResponseEntity getTeacherById(@PathVariable("Id") Integer id ) throws DepartmentException{
        try {
            Department department = departmentService.getDepartmentById(id);
            return new ResponseEntity<Department>(department,HttpStatus.OK);
        }catch (DepartmentException ex){
            String errorMessage = "Error: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
