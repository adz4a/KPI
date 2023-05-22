package com.program.service.serviceImpl;

import com.program.exception.SubmissionException;
import com.program.model.Event;
import com.program.model.submission.Submission;
import com.program.model.submission.TeacherSubmission;
import com.program.model.submission.TeacherSubmissionId;
import com.program.model.teacher.Teacher;
import com.program.model.teacher.TeacherEvent;
import com.program.repository.*;
import com.program.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;


@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private TeacherEventRepository teacherEventRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TeacherSubmissionRepository teacherSubmissionRepository;


    @Override
    public void saveSubmit(Long userId,Integer eventId,MultipartFile file) throws SubmissionException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if(fileName.contains("..")) {
                throw new SubmissionException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Teacher teacher = teacherRepository.findByUserId(userId);
            Event event = eventRepository.findEventById(eventId);
            TeacherEvent teacherEvent = teacherEventRepository.findEventAndTeacherId(teacher.getTeacherId(), eventId);

            if (teacherEvent!= null) {
                teacherEvent.setSubmissionStatus(true);
                teacherEventRepository.save(teacherEvent);
            }

            Submission submission = new Submission(fileName, file.getContentType(),file.getSize(), file.getBytes());
            submissionRepository.save(submission);

            TeacherSubmissionId teacherSubmissionId = new TeacherSubmissionId(teacher.getTeacherId(),event.getEventId(),submission.getSubmissionId());
            TeacherSubmission teacherSubmission = new TeacherSubmission(teacherSubmissionId,teacher,event,submission);
            teacherSubmissionRepository.save(teacherSubmission);


        } catch (IOException ex) {
            throw new SubmissionException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Submission getSubmission(String id) throws Exception {
        return submissionRepository
                .findBySubmissionId(id);
//                .orElseThrow(
//                        () -> new Exception("File not found with Id: " + id));
    }

}
