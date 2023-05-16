package com.program.service;

import com.program.exception.SubmissionException;
import com.program.model.submission.Submission;
import org.springframework.web.multipart.MultipartFile;

public interface SubmissionService {

    Submission saveSubmit(MultipartFile multipartFile) throws SubmissionException;

    Submission getSubmission(String id) throws Exception;
}
