package com.program.controller;

import com.program.exception.SubmissionException;
import com.program.exception.TeacherException;
import com.program.exception.UserException;
import com.program.helper.jwt.JwtUtils;
import com.program.model.User;
import com.program.model.submission.Submission;
import com.program.service.JwtService;
import com.program.service.SubmissionService;
import com.program.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;


@RestController
public class SubmissionController {

    @Autowired
    public SubmissionService submissionService;

    @Autowired
    public JwtUtils jwtUtils;

    @Autowired
    public UserService userService;

    @Autowired
    public JwtService jwtService;


    @PostMapping("event/{eventId}/upload")
    public void uploadFile(HttpServletRequest request, @PathVariable Integer eventId, @RequestParam("file") MultipartFile file) throws SubmissionException, UserException {

        String token = jwtService.extractBearerToken(request);
        String email = jwtUtils.getEmailFromJwtToken(token);
        User user = userService.isUserEmailPresent(email);

        if (user != null) {
            Long userId = user.getUserId();
            submissionService.saveSubmit(userId, eventId,file);
        }

    }

    @PostMapping("/event/{eventId}/uploads")
    public void uploadMultipleFiles(HttpServletRequest request, @PathVariable Integer eventId, @RequestParam("files") MultipartFile[] files) throws SubmissionException, UserException {
            for (MultipartFile file : files) {
                uploadFile(request, eventId, file);
            }

    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request,@PathVariable String fileId) throws Exception {
        String token = jwtService.extractBearerToken(request);
        String email = jwtUtils.getEmailFromJwtToken(token);
        User user = userService.isUserEmailPresent(email);

        Submission submission = submissionService.getSubmission(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(submission.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + submission.getFileName()
                                + "\"")
                .body(new ByteArrayResource(submission.getData()));
    }
}
