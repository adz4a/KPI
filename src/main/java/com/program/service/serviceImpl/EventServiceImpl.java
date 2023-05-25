package com.program.service.serviceImpl;

import com.program.model.Event;
import com.program.exception.EventException;
import com.program.model.Status;
import com.program.repository.EventRepository;
import com.program.repository.StatusRepository;
import com.program.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Event addNewEvent(Integer statusId, Event event) throws EventException {
        if (event!=null) {
            Status status = statusRepository.findByStatusId(statusId);

            if (status != null) {
                event.setStatus(status);
                return eventRepository.save(event);
            }
            else {
                throw new EventException("Status that you indicated doesn't exist! Status Id: " + statusId);
            }
        }
        else {
            throw new EventException("Category details is Empty...");
        }
    }

    @Override
    public Event getEventById(Integer eventId) throws EventException {
        Optional<Event> opt= eventRepository.findById(eventId);
        if(opt.isPresent()) {
            return opt.get();
        }
        else {
            throw new EventException("Event does not exist with Id : "+eventId);

        }
    }

    @Override
    public void updateEventById(Integer id, Event event) throws EventException {
        if (event == null) {
            throw new EventException("Event details is Empty...");
        }

        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            throw new EventException("Event with ID " + id + " does not exist.");
        }
        Event existingEvent = optionalEvent.get();
//		Setter
        existingEvent.setEventName(event.getEventName());
        existingEvent.setEventPercentage(event.getEventPercentage());

//        Status status = statusRepository.;
//        if (status != null) {
//            existingEvent.setStatus(status);
//            existingEvent.setEventPercentage(event.getEventPercentage());
//        } else {
//            throw new EventException("Category with this name or Status with this name doesn't exist!");
//        }

        eventRepository.save(existingEvent);
    }

    @Override
    public void deleteEventById(Integer id) throws EventException {
        Optional<Event> opt= eventRepository.findById(id);
        if(opt.isPresent()) {
            eventRepository.deleteByEventId(id);
        }
        else {
            throw new EventException("Event does not exist with Id : "+ id);

        }
    }

}
