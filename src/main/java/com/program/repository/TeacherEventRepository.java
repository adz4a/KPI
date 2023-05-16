package com.program.repository;

import com.program.model.teacher.TeacherEvent;
import com.program.model.teacher.TeacherEventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TeacherEventRepository extends JpaRepository<TeacherEvent, TeacherEventId> {
}
