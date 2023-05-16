package com.program.service;

import com.program.exception.TeacherException;
import com.program.model.teacher.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> findAllTeacher() throws TeacherException;

    Teacher getTeacherById(Long id) throws TeacherException;

}
