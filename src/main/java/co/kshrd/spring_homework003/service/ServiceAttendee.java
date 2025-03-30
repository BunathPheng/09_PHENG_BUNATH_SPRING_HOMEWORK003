package co.kshrd.spring_homework003.service;


import co.kshrd.spring_homework003.model.dto.request.AttendeesRequest;
import co.kshrd.spring_homework003.model.entity.Attendee;

import java.util.List;

public interface ServiceAttendee {
    List<Attendee> getAllAttendee(Integer offset , Integer limit);
    Attendee getAttendeeById(Integer attendeeId);
    Attendee insertAttendeeTo(AttendeesRequest attendeeRequest);
    Attendee updateAttendeeById(Integer attendeeId , AttendeesRequest attendeesRequest);
    Boolean deleteAttendeeById (Integer attendeeId);

}
