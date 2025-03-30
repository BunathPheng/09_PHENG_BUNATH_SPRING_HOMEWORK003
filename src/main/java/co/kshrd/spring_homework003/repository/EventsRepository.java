package co.kshrd.spring_homework003.repository;

import co.kshrd.spring_homework003.model.dto.request.EventRequest;
import co.kshrd.spring_homework003.model.entity.Attendee;
import co.kshrd.spring_homework003.model.entity.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface EventsRepository {
    @Results(id = "eventMapper" ,  value = {
            @Result(property = "eventId" , column = "event_id") ,
            @Result(property = "eventName" , column = "event_name"),
            @Result(property= "date" , column = "event_date"),
            @Result(property = "venue", column = "venues_id",
            one = @One(select = "co.kshrd.spring_homework003.repository.VenueRepository.findVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "co.kshrd.spring_homework003.repository.EventsRepository.findAttendeeByEventId"))
    })
    @Select(
            """
              select * from events 
              offset #{limit} * (#{offset} - 1)
              limit #{limit}
            """
    )
    List<Event> getAllEvent(Integer offset , Integer limit);
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    @Select("""
        SELECT a.* FROM attendees a 
        INNER JOIN event_attendee e ON a.attendee_id = e.attendee_id
        WHERE e.event_id = #{eventId}
    """)
    List<Attendee> findAttendeeByEventId(@Param("eventId") Integer eventId);
    @Select(
            """
            select * from events
            where event_id = #{eventId}
            """
    )
    @ResultMap("eventMapper")
    Event getEventById (Integer eventId);

    @Select(
    """
    delete from events
    where event_id = #{eventId}
    returning event_id;
    """
    )
    Integer deleteEventById(Integer eventId);

    @Select("""
        INSERT INTO events(event_name, event_date, venues_id) 
        VALUES (#{event.eventName},#{event.eventDate},#{event.venuesId}) 
        RETURNING event_id
""")
    Integer insertEvent     (@Param("event")EventRequest eventRequest);
    @Select(
    """
    INSERT INTO event_attendee(event_id, attendee_id)
    VALUES (#{eventId}, #{attendeeId})
    returning *
    """
    )
    Event insertEventAttendee(Integer eventId, Integer attendeeId);

    //select check venueId
    @Select("SELECT COUNT(*) FROM venues WHERE venue_id = #{venueId}")
    boolean existsById(@Param("venueId") Integer venueId);

    // Update event By Id
    @Select("""
    UPDATE events SET event_name = #{event.eventName} ,event_date = #{event.eventDate}, venues_id = #{event.venuesId}
    WHERE event_id = #{eventId} RETURNING event_id
    """)
    Integer findUpdateById(Integer eventId, @Param("event")EventRequest eventRequest);

    // Update EventAttendee
    @Select(
    """
    INSERT INTO event_attendee(event_id, attendee_id)
    VALUES (#{eventId}, #{attendeeId})
    returning *
    """)
    Event updateEventAttendee(Integer eventId, Integer attendeeId);

}
