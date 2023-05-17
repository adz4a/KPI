package com.program.controller.teacher;

import com.program.exception.CategoryException;
import com.program.exception.TeacherException;
import com.program.model.Category;
import com.program.model.teacher.TeacherEvent;
import com.program.service.TeacherEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherEventController {

    @Autowired
    public TeacherEventService teacherEventService;

    @GetMapping("/teacher/events")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('OBSERVER')")
    public ResponseEntity<List<TeacherEvent>> getAllCategories() throws TeacherException {
        List<TeacherEvent> teacherEvents =	teacherEventService.getAllTeacherEvent();
        return new ResponseEntity<List<TeacherEvent>>(teacherEvents,HttpStatus.OK);
    }

    @GetMapping("category/status/event/{eventId}/teachers")
    public ResponseEntity<List<TeacherEvent>> getTeachersByEvents( @PathVariable("eventId") Integer eventId ) throws TeacherException {
        List<TeacherEvent> teacherEvents = teacherEventService.getTeachersByEvent(eventId);
        return new ResponseEntity<List<TeacherEvent>>(teacherEvents, HttpStatus.OK);
    }
}
