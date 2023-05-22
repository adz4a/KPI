package com.program.repository;

import com.program.model.submission.TeacherSubmissionId;
import com.program.model.submission.TeacherSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherSubmissionRepository extends JpaRepository<TeacherSubmission, TeacherSubmissionId> {

}
