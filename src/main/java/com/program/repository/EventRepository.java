package com.program.repository;

import com.program.model.Event;
import com.program.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

//    Event findByStatusAndCategory(String status, String category);
    Event save(Integer eventId);

}
