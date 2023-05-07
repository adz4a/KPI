package com.program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
