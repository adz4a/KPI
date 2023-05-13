package com.program.service.serviceImpl;

import com.program.exception.StatusException;
import com.program.model.Category;
import com.program.model.Event;
import com.program.exception.EventException;
import com.program.model.Status;
import com.program.model.Teacher;
import com.program.repository.EventRepository;
import com.program.repository.StatusRepository;
import com.program.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Event> getAllEvents() throws EventException {
        return eventRepository.findAll();
    }

    @Override
    public Event addNewEvent(Event event) throws EventException {
        if (event!=null) {
            Status status = statusRepository.findByCategoryAndStatusName(event.getEventCategory(), event.getEventStatus());

            if (status != null) {
                event.setStatus(status);
                return eventRepository.save(event);
            }
            else {
                return null;
            }
        }
        else {
            throw new EventException("Status details is Empty...");
        }
    }

    @Override
    public Event getEventById(Integer eventId) throws EventException {
        Optional<Event> opt= eventRepository.findById(eventId);
        if(opt.isPresent()) {
            return opt.get();
        }
        else {
            throw new EventException("Status does not exist with Id :"+eventId);

        }
    }

    @Override
    public Event updateEventById(Integer id, Event event) throws EventException {
        if (event == null) {
            throw new EventException("Status details is Empty...");
        }

        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            throw new EventException("Status with ID " + id + " does not exist.");
        }
        Event existingEvent = optionalEvent.get();
//		Setter
        existingEvent.setEventName(event.getEventName());

        Status status = statusRepository.findByCategoryAndStatusName(event.getEventCategory(), event.getEventStatus());
        if (status != null) {
            existingEvent.setStatus(status);
            existingEvent.setEventStatus(event.getEventStatus());
            existingEvent.setEventCategory(event.getEventCategory());
        } else {
            throw new EventException("Category with name " + event.getEventCategory() + " or Status with name " + event.getEventStatus() + " doesn't exist!");
        }

        return eventRepository.save(existingEvent);
    }

    @Override
    public void deleteEventById(Integer id) throws EventException {
        eventRepository.deleteByEventId(id);
    }

}
