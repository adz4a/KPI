package com.program.service.serviceImpl;

import com.program.exception.SubmissionException;
import com.program.model.submission.Submission;
import com.program.model.teacher.Teacher;
import com.program.model.teacher.TeacherEvent;
import com.program.repository.SubmissionRepository;
import com.program.repository.TeacherEventRepository;
import com.program.repository.TeacherRepository;
import com.program.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


@Service
public class SubmissionServiceImpl implements SubmissionService {

    private SubmissionRepository submissionRepository;

    @Autowired
    private TeacherEventRepository teacherEventRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Override
    public void saveSubmit(Long userId,Integer eventId,MultipartFile file) throws SubmissionException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if(fileName.contains("..")) {
                throw new SubmissionException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Submission submission = new Submission( fileName, file.getContentType(),file.getSize(), file.getBytes());

            submissionRepository.save(submission);

            Teacher teacher = teacherRepository.findByUserId(userId);
            TeacherEvent teacherEvent = teacherEventRepository.findEventAndTeacherId(teacher.getTeacherId(), eventId);

            if (teacherEvent!= null) {
                teacherEvent.setSubmissionStatus(true);
                teacherEvent.setSubmission(submission);
                teacherEventRepository.save(teacherEvent);
            }


        } catch (IOException ex) {
            throw new SubmissionException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Submission getSubmission(String id) throws Exception {
        return submissionRepository
                .findById(id)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + id));
    }

    @Override
    public void setSubmissionIdForTeacher(Long userId,Integer eventId,Submission submission) throws SubmissionException {
        Submission existingSubmission = submissionRepository.findBySubmissionId(submission.getSubmissionId());
        if (existingSubmission!= null){
            Teacher teacher = teacherRepository.findByUserId(userId);
            TeacherEvent teacherEvent = teacherEventRepository.findEventAndTeacherId(teacher.getTeacherId(), eventId);

            teacherEvent.setSubmissionStatus(true);
            teacherEvent.setSubmission(existingSubmission);

            teacherEventRepository.save(teacherEvent);

        }
        else
            throw new SubmissionException("Submitted file that you indicate doesn't exist!");
    }
}
