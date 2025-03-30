package co.kshrd.spring_homework003.repository;

import co.kshrd.spring_homework003.model.dto.request.AttendeesRequest;
import co.kshrd.spring_homework003.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Results(id = "AttendeeMapper" , value = {
            @Result(property = "attendeeId" , column = "attendee_id"),
            @Result(property = "attendeeName" , column = "attendee_name")
    })

    @Select(
    """
      select * from attendees 
      offset #{limit} * (#{offset} - 1)
      limit #{limit}
    """
    )
    List<Attendee>  getAllAttendee(Integer offset , Integer limit);

    @Select(
    """
    select * from attendees
    where attendee_id = #{attendeeId}
    """
    )
    @ResultMap("AttendeeMapper")
    Attendee getAttendeeById(Integer attendeeId);
    @Select("""
        INSERT INTO attendees (attendee_name, email)
        VALUES (#{attendee.attendeeName}, #{attendee.email})
        RETURNING *;
    """)
    @ResultMap("AttendeeMapper")
    Attendee insertAttendeeTo(@Param("attendee") AttendeesRequest attendeeRequest);


    @Select(
            """
            update attendees set attendee_name = #{attendee.attendeeName} ,  email = #{attendee.email}
            where attendee_id = #{attendeeId}
            returning * ;
            """
    )
    @ResultMap("AttendeeMapper")
    Attendee updateAttendeeById(Integer attendeeId , @Param("attendee")AttendeesRequest attendeeRequest);

    @Select(
    """
    delete from attendees
    where attendee_id = #{attendeeId}
    returning attendee_id;
    """
    )
    Integer deleteAttendeeById(Integer attendeeId);



}
