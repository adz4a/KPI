package com.program.controller;


import com.program.exception.AssignException;
import com.program.model.teacher.Teacher;
import com.program.payload.request.AssignRequest;
import com.program.payload.request.EmailRequest;
import com.program.service.AssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

@RestController
public class AssignController {

    @Autowired
    public AssignService assignService;


    @GetMapping("/teacher/detail")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getTeacherDetail(@RequestBody EmailRequest emailRequest) throws AssignException {
        try {
            Teacher teacher = assignService.getTeacherDetail(emailRequest);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } catch (AssignException ex) {
            String errorMessage = "Error setting: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity assignEventsForTeacher(@RequestBody AssignRequest assignRequest) throws AssignException {
        try {
            assignService.assignTeacherEvents(assignRequest);
            return new ResponseEntity<>("Assign events for  teachers has been successfully!", HttpStatus.OK);
        } catch (AssignException ex) {
            String errorMessage = "Error setting: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
