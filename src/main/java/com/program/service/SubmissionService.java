package com.program.service;

import com.program.exception.SubmissionException;
import com.program.model.submission.Submission;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubmissionService {

    void saveSubmit(Long userId,Integer eventId,MultipartFile multipartFile) throws SubmissionException;

    Submission getSubmission(String submissionId) throws Exception;

    List<Submission> getSubmissions(Long teacherId, Integer eventId) throws SubmissionException;

    void deleteSubmission(Long userId,String submissionId) throws SubmissionException;


}
