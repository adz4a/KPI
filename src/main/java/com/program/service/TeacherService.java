package com.program.service;

import com.program.exception.TeacherException;
import com.program.exception.UserException;
import com.program.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> findAllTeacher() throws TeacherException;

    Teacher getTeacherByEmail(String email) throws TeacherException;

}
