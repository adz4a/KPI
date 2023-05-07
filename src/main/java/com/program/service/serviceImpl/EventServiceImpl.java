package com.program.service.serviceImpl;

import com.program.model.Event;
import com.program.exception.EventException;
import com.program.service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Override
    public Event getEventById(Integer eventId) throws EventException {
        return null;
    }

}
