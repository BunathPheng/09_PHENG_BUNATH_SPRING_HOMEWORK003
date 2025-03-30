package co.kshrd.spring_homework003.service.serviceImpl;

import co.kshrd.spring_homework003.exception.NotFoundExceptionHandler;

import co.kshrd.spring_homework003.model.dto.request.AttendeesRequest;
import co.kshrd.spring_homework003.model.entity.Attendee;
import co.kshrd.spring_homework003.model.entity.Venues;
import co.kshrd.spring_homework003.repository.AttendeeRepository;
import co.kshrd.spring_homework003.service.ServiceAttendee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceAttendeeImpl implements ServiceAttendee {
    private final AttendeeRepository attendeeRepository;
    @Override
    public List<Attendee> getAllAttendee(Integer offset, Integer limit) {
        List<Attendee> attendees = attendeeRepository.getAllAttendee(offset , limit);
        return attendees;
    }

    @Override
    public Attendee getAttendeeById(Integer attendeeId) {
         Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
         if (attendee == null){
             throw new NotFoundExceptionHandler("Attendee is not fount " + attendeeId);
         }
        return attendee;
    }

    @Override
    public Attendee insertAttendeeTo(AttendeesRequest attendeesRequest) {
        Attendee attendee = attendeeRepository.insertAttendeeTo(attendeesRequest);
        return attendee;
    }

    @Override
    public Attendee updateAttendeeById(Integer attendeeId, AttendeesRequest attendeesRequest) {
        Attendee attendee = attendeeRepository.updateAttendeeById(attendeeId ,attendeesRequest);
        if(attendee == null){
            throw new NotFoundExceptionHandler("Attendee is not found with id:" + attendeeId);
        }
        return attendee;
    }

    @Override
    public Boolean deleteAttendeeById(Integer attendeeId) {
        Integer  attendee = attendeeRepository.deleteAttendeeById(attendeeId);
        if(attendee == null){
            throw new NotFoundExceptionHandler("Attendee id is not found" + attendeeId);
        }
        return true;
    }

}
