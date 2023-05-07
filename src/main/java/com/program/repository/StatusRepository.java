package com.program.repository;

import com.program.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends  JpaRepository<Status, Integer> {

	Status save(Integer productId);


}
