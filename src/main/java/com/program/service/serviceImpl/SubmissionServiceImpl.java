package com.program.service.serviceImpl;

import com.program.exception.SubmissionException;
import com.program.model.submission.Submission;
import com.program.repository.SubmissionRepository;
import com.program.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;


@Service
public class SubmissionServiceImpl implements SubmissionService {

    private SubmissionRepository submissionRepository;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Override
    public Submission saveSubmit(MultipartFile file) throws SubmissionException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if(fileName.contains("..")) {
                throw new SubmissionException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Submission submission = new Submission( fileName, file.getContentType(),file.getSize(), file.getBytes());
            return submissionRepository.save(submission);

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
}
