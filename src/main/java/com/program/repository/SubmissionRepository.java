package com.program.repository;

import com.program.model.submission.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SubmissionRepository extends JpaRepository<Submission,String> {

//    @Query("SELECT s FROM Submission s WHERE s.submissionValue =:submissionValue")
//    Submission findByValue(String submissionValue) throws Exception;

}
