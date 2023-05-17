package com.program.repository;

import com.program.model.teacher.TeacherEvent;
import com.program.model.teacher.TeacherEventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TeacherEventRepository extends JpaRepository<TeacherEvent, TeacherEventId> {

    @Query("SELECT t FROM TeacherEvent t WHERE t.event.eventId = :eventId")
    List<TeacherEvent> findTeachersByEventId(Integer eventId);

    @Query("SELECT t.teacher.teacherId FROM TeacherEvent t WHERE t.teacher.teacherId = :teacherId")
    List<TeacherEvent> findByTeacherId(Long teacherId);

}
