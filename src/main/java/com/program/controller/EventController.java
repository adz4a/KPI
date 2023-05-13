package com.program.controller;

import com.program.exception.EventException;
import com.program.exception.StatusException;
import com.program.model.Event;
import com.program.model.Status;
import com.program.repository.EventRepository;
import com.program.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    public EventService eventService;

    @Autowired
    public EventRepository eventRepository;


    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() throws EventException
    {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    @PostMapping("/event/add")
    public HttpEntity<? extends Object> addNewEvent(@RequestBody Event event) throws EventException
    {
        Event newEvent = eventService.addNewEvent(event);

        if (newEvent==null) {
            return new ResponseEntity<String>("Status details is empty or status/category which you indicated does not exist!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Event>(newEvent, HttpStatus.OK);
        }
    }

    @GetMapping("/event/getById/{Id}")
    public ResponseEntity<Event> getStatusById(@PathVariable("Id") Integer id ) throws EventException
    {
        Event event = eventService.getEventById(id);
        return new ResponseEntity<Event>(event,HttpStatus.OK);
    }

    @PutMapping("/event/update/{id}")
    public ResponseEntity<Object> updateEventById(@PathVariable Integer id,@RequestBody Event event) throws EventException {
        try {
            Event updatedEvent = eventService.updateEventById(id, event);
            return ResponseEntity.ok(updatedEvent);
        } catch (EventException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/event/delete/{Id}")
    public ResponseEntity<Object> deleteEventById(@PathVariable ("Id") Integer id ) throws EventException
    {
        eventService.deleteEventById(id);
        return new ResponseEntity<>("Event with this Id deleted",HttpStatus.OK);
    }

}
