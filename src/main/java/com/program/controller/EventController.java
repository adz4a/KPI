package com.program.controller;

import com.program.exception.EventException;
import com.program.model.Event;
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

    @PostMapping("status/{Id}/event/add")
    public HttpEntity<? extends Object> addNewEvent(@PathVariable("Id") Integer id, @RequestBody Event event) throws EventException
    {
        Event newEvent = eventService.addNewEvent(id,event);

        if (newEvent==null) {
            return new ResponseEntity<String>("Status details is empty!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Event>(newEvent, HttpStatus.OK);
        }
    }

    @GetMapping("status/event/getById/{Id}")
    public ResponseEntity<Event> getEventById(@PathVariable("Id") Integer id ) throws EventException
    {
        Event event = eventService.getEventById(id);
        return new ResponseEntity<Event>(event,HttpStatus.OK);
    }

    @GetMapping("status/event/update/{Id}")
    public ResponseEntity<Event> updateEventById(@PathVariable("Id") Integer id ) throws EventException
    {
        Event event = eventService.getEventById(id);
        return new ResponseEntity<Event>(event,HttpStatus.OK);
    }

    @PutMapping("status/event/update/{id}")
    public ResponseEntity<Object> updateEventById(@PathVariable Integer id,@RequestBody Event event) throws EventException {
        try {
            Event updatedEvent = eventService.updateEventById(id, event);
            return ResponseEntity.ok(updatedEvent);
        } catch (EventException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("status/event/delete/{Id}")
    public ResponseEntity<Object> deleteEventById(@PathVariable ("Id") Integer id ) throws EventException
    {
        eventService.deleteEventById(id);
        return new ResponseEntity<>("Event with this Id deleted",HttpStatus.OK);
    }

}
