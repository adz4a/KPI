package com.program.service.serviceImpl;

import com.program.exception.TeacherException;
import com.program.model.Event;
import com.program.model.Status;
import com.program.model.teacher.Teacher;
import com.program.model.teacher.TeacherEvent;
import com.program.repository.*;
import com.program.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TeacherEventRepository teacherEventRepository;


    @Override
    public List<Teacher> findAllTeacher() throws TeacherException {
        return teacherRepository.findAll();
    }


    @Override
    public Teacher getTeacherById(Long id) throws TeacherException {
        return teacherRepository.findByUserId(id);
    }


}
