package com.program.service.serviceImpl;

import com.program.exception.TeacherEventException;
import com.program.exception.TeacherException;
import com.program.model.approve.Approve;;
import com.program.model.teacher.TeacherEvent;
import com.program.repository.ApproveRepository;
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
    private TeacherEventRepository teacherEventRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ApproveRepository approveRepository;


    @Override
    public List<TeacherEvent> getAllTeacherEvent() throws TeacherEventException {
        return teacherEventRepository.findAll();
    }

    @Override
    public List<TeacherEvent> getTeachersByEvent(Integer eventId) throws TeacherEventException {

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
    public void setEventApprove(Long teacherId, Integer eventId, Approve approve) throws TeacherEventException{
        TeacherEvent existingTeacherEvent = teacherEventRepository.findEventAndTeacherId(teacherId,eventId);

        Approve existingApprove = approveRepository.findApproveByName(approve.getApproveName());
        if (existingApprove == null){
            throw new TeacherEventException("This approve status doesn't exist");
        }
        existingTeacherEvent.setApprove(existingApprove);
        teacherEventRepository.save(existingTeacherEvent);
    }



}
