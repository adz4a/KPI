package com.program.repository;

import com.program.model.submission.TeacherSubmissionId;
import com.program.model.submission.TeacherSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TeacherSubmissionRepository extends JpaRepository<TeacherSubmission, TeacherSubmissionId> {

}
