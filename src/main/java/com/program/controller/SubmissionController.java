package com.program.controller;

import com.program.exception.SubmissionException;
import com.program.model.submission.ResponseData;
import com.program.model.submission.Submission;
import com.program.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class SubmissionController {

    public SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file") MultipartFile file) throws SubmissionException {
        Submission submission = submissionService.saveSubmit(file);

        String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(submission.getSubmissionId())
                .toUriString();

        return new ResponseData(submission.getFileName(), downloadURl, file.getContentType(), file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Submission submission = submissionService.getSubmission(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(submission.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + submission.getFileName()
                                + "\"")
                .body(new ByteArrayResource(submission.getData()));
    }
}
