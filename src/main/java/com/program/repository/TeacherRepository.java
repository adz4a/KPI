package com.program.repository;

import com.program.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("SELECT t FROM Teacher t WHERE t.user.userId = :userId")
    Teacher findByUserId(Integer userId);

    @Query("SELECT t FROM Teacher t WHERE t.status.statusId = :statusId")
    List<Teacher> findByStatusId(Integer statusId);

    @Modifying
    @Query("UPDATE Teacher t SET t.status = null WHERE t.status.statusId = :statusId")
    void resetStatusByStatusId(Integer statusId);

    @Modifying
    @Query("DELETE FROM Teacher t WHERE t.status.statusId = :statusId")
    void deleteByStatusId(Integer statusId);


}
