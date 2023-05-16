package com.program.service.serviceImpl;

import com.program.exception.TeacherException;
import com.program.model.teacher.Teacher;
import com.program.repository.TeacherRepository;
import com.program.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    private Teacher teacher;

    @Override
    public List<Teacher> findAllTeacher() throws TeacherException {
        return teacherRepository.findAll();
    }


    @Override
    public Teacher getTeacherById(Long id) throws TeacherException {
        return teacherRepository.findByUserId(id);
    }
}
