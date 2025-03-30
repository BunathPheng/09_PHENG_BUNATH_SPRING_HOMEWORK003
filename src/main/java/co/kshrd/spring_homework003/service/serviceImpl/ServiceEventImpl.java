package co.kshrd.spring_homework003.service.serviceImpl;
import co.kshrd.spring_homework003.exception.NotFoundExceptionHandler;
import co.kshrd.spring_homework003.model.dto.request.EventRequest;
import co.kshrd.spring_homework003.model.entity.Event;
import co.kshrd.spring_homework003.repository.EventsRepository;
import co.kshrd.spring_homework003.service.ServiceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ServiceEventImpl implements ServiceEvent {
    private final EventsRepository eventsRepository;
    @Override
    public List<Event> getAllEvent(Integer offset, Integer limit) {
        List<Event> event = eventsRepository.getAllEvent(offset,limit);
        if(event == null){
            throw new NotFoundExceptionHandler("Is not found");
        }
        return event;
    }

    @Override
    public Event getAllEventById(Integer eventId) {
        Event event = eventsRepository.getEventById(eventId);
        if (event == null){
            throw  new NotFoundExceptionHandler("Event is not found  by id :" + eventId);
        }
        return event;
    }

    @Override
    public Boolean deleteEventById(Integer eventId) {
        Integer event = eventsRepository.deleteEventById(eventId);
        if (event == null)
        {
            throw  new NotFoundExceptionHandler("Event id is not found: " + eventId);
        }
        return true;
    }

    @Override
    public Event insertEvent(EventRequest eventRequest) {

        boolean venueExists = eventsRepository.existsById(eventRequest.getVenuesId());
        if (!venueExists) {
            throw new NotFoundExceptionHandler("Venue ID " + eventRequest.getVenuesId() + " does not exist.");
        }

        Integer eventId = eventsRepository.insertEvent(eventRequest);
        if (eventId == null) {
            throw new NotFoundExceptionHandler("Failed to insert event.");
        }

        for (Integer attendeeId : eventRequest.getAttendeeId()) {
            eventsRepository.insertEventAttendee(eventId, attendeeId);
        }

        return eventsRepository.getEventById(eventId);
    }


    @Override
    public Event updateEventById(Integer eventId, EventRequest eventRequest) {
        // Check if venue exists
        boolean venueExists = eventsRepository.existsById(eventRequest.getVenuesId());
        if (!venueExists) {
            throw new NotFoundExceptionHandler("Venue ID " + eventRequest.getVenuesId() + " does not exist.");
        }
        Event event = new Event();
        Integer updatedEventId = eventsRepository.findUpdateById(eventId, eventRequest);
        if (updatedEventId == null) {
            throw new NotFoundExceptionHandler("Event ID " + eventId + " not found or not updated.");
        }

            for (Integer attendeeId : eventRequest.getAttendeeId()) {
              event =  eventsRepository.updateEventAttendee(eventId, attendeeId);

        }
        return event;
    }
}
