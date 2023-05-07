package com.program.controller;

import com.program.repository.EventRepository;
import com.program.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    EventRepository eventRepository;
}
