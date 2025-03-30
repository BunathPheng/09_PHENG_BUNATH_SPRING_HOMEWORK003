package co.kshrd.spring_homework003.service;

import co.kshrd.spring_homework003.model.dto.request.EventRequest;
import co.kshrd.spring_homework003.model.entity.Event;

import java.util.List;

public interface ServiceEvent {
    List<Event> getAllEvent(Integer offset , Integer limit);
    Event getAllEventById(Integer eventId);
    Boolean deleteEventById(Integer eventId);
    Event insertEvent(EventRequest eventRequest);
    Event updateEventById(Integer eventId , EventRequest eventRequest);
}
