package com.program.controller;

import com.program.exception.TeacherException;
import com.program.model.Teacher;
import com.program.service.TeacherService;
import com.program.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class TeacherController {

    @Autowired
    public TeacherService teacherService;

    @Autowired
    public UserService userService;

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() throws TeacherException {
        List<Teacher> teachers = teacherService.findAllTeacher();
        return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
    }

    @GetMapping("/teacher/getById/{Id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("Id") Integer id ) throws TeacherException{
        Teacher teacher = teacherService.getTeacherById(id);
        return new ResponseEntity<Teacher>(teacher,HttpStatus.OK);
    }


}
