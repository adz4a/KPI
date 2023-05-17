package com.program.service.serviceImpl;

import com.program.exception.TeacherException;
import com.program.model.Event;
import com.program.model.Status;
import com.program.model.teacher.Teacher;
import com.program.model.teacher.TeacherEvent;
import com.program.repository.EventRepository;
import com.program.repository.StatusRepository;
import com.program.repository.TeacherEventRepository;
import com.program.repository.TeacherRepository;
import com.program.service.TeacherEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherEventServiceImpl implements TeacherEventService {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TeacherEventRepository teacherEventRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public List<TeacherEvent> getAllTeacherEvent() throws TeacherException {
        return teacherEventRepository.findAll();
    }

    @Override
    public List<TeacherEvent> getTeachersByEvent(Integer eventId) throws TeacherException {

        List<TeacherEvent> teacherEvents = teacherEventRepository.findTeachersByEventId(eventId);
        if (!teacherEvents.isEmpty()) {
            List<TeacherEvent> newTeacherEvent = new ArrayList<>();
            for (TeacherEvent teacherEvent : teacherEvents){
                newTeacherEvent.add(teacherEvent);
            }
            return newTeacherEvent;

        }

        return null;
    }

}
