package com.program.repository;

import com.program.model.Teacher;
import com.program.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

//    Teacher save(Integer teacherId);
//    Teacher findByRole(String categoryName, String statusName);

    Teacher findByEmail(String email);


}
