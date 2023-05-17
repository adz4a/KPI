package com.program.controller;

import com.program.exception.SubmissionException;
import com.program.exception.TeacherException;
import com.program.exception.UserException;
import com.program.helper.jwt.JwtUtils;
import com.program.model.User;
import com.program.model.teacher.Teacher;
import com.program.payload.response.SubmissionResponseData;
import com.program.model.submission.Submission;
import com.program.service.SubmissionService;
import com.program.service.TeacherEventService;
import com.program.service.TeacherService;
import com.program.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SubmissionController {

    public SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    public UserService userService;

    @Autowired
    public TeacherService teacherService;

    @Autowired
    public TeacherEventService teacherEventService;

    private String extractBearerToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }


    @PostMapping("event/{eventId}/upload")
    @PreAuthorize("hasRole('TEACHER')")
    public HttpEntity<? extends Object> uploadFile(HttpServletRequest request, @PathVariable Integer eventId, @RequestParam("file") MultipartFile file) throws SubmissionException, UserException, TeacherException {

        String token = extractBearerToken(request);
        String email = jwtUtils.getEmailFromJwtToken(token);
        User user = userService.isUserEmailPresent(email);

        if (user != null) {
                Long userId = user.getUserId();
//                Teacher teacher = teacherService.getTeacherById(id);

        Submission submission = submissionService.saveSubmit(userId,eventId,file);
        String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(submission.getSubmissionId())
                .toUriString();

        SubmissionResponseData responseData = new SubmissionResponseData(submission.getFileName(), downloadURl, file.getContentType(), file.getSize());

        return new ResponseEntity<SubmissionResponseData>(responseData,HttpStatus.OK);
        }else
            return null;

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
