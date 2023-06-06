package com.program.service;

import com.program.exception.AssignException;
import com.program.model.teacher.Teacher;
import com.program.payload.request.AssignRequest;
import com.program.payload.request.EmailRequest;

public interface AssignService {


    Teacher getTeacherDetail(EmailRequest emailRequest) throws AssignException;

    void assignTeacherEvents(AssignRequest assignRequest) throws AssignException;

}
