package com.program.service.serviceImpl;

import com.program.exception.TeacherException;
import com.program.model.Teacher;
import com.program.repository.TeacherRepository;
import com.program.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAllTeacher() throws TeacherException {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherByEmail(String email) throws TeacherException {
        return teacherRepository.findByEmail(email);
    }
}
