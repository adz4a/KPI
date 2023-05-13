package com.program.service;

import com.program.exception.TeacherException;
import com.program.exception.UserException;
import com.program.model.Teacher;
import com.program.model.User;

import java.util.List;

public interface TeacherService {

    List<Teacher> findAllTeacher() throws TeacherException;

    Teacher getTeacherById(Integer id) throws TeacherException;

}
