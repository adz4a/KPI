package com.program.service;

import com.program.model.Event;
import com.program.exception.EventException;

public interface EventService {

    Event getEventById(Integer eventId) throws EventException;

}
