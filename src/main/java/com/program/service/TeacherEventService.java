package com.program.service;

import com.program.exception.TeacherException;
import com.program.model.teacher.TeacherEvent;

import java.util.List;

public interface TeacherEventService {

    List<TeacherEvent> getAllTeacherEvent() throws TeacherException;

    List<TeacherEvent> getTeachersByEvent( Integer eventId) throws TeacherException;

}
