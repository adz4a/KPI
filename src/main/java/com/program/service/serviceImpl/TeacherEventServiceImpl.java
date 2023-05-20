package com.program.service.serviceImpl;

import com.program.exception.TeacherException;
import com.program.model.approve.Approve;;
import com.program.model.teacher.TeacherEvent;
import com.program.repository.TeacherEventRepository;
import com.program.repository.TeacherRepository;
import com.program.service.TeacherEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherEventServiceImpl implements TeacherEventService {


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

    @Override
    public void setEventApprove(Long teacherId, Integer eventId, Approve approve) {
        TeacherEvent existingTeacherEvent = teacherEventRepository.findEventAndTeacherId(teacherId,eventId);
        existingTeacherEvent.setApprove(approve);
        teacherEventRepository.save(existingTeacherEvent);
    }



}
