package co.kshrd.spring_homework003.repository;

import co.kshrd.spring_homework003.model.dto.request.VenueRequest;
import co.kshrd.spring_homework003.model.entity.Venues;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {
     @Results(id = "VenuesMapper" ,  value = {
             @Result(property = "venueId" , column = "venue_id") ,
             @Result(property = "venueName" , column = "venue_name")
     })
     @Select("""
    SELECT * FROM venues
    offset #{size} * (#{page} -1)
    limit #{size}
     """)
     List<Venues> getAllVenues(Integer page , Integer size);


     @Select(
             """
    select * from venues where venue_id = #{id};
"""
     )
     @ResultMap("VenuesMapper")
     Venues findVenueById(Integer venueId);

     @Select(
      """
            insert into venues values (default , #{venueName} , #{location} )
            returning *;
     """
     )
     @ResultMap("VenuesMapper")
     Venues insertVenue(VenueRequest venueRequest);

     @Select(
             """
             DELETE FROM venues
             WHERE venue_id = #{venueId}
             RETURNING venue_id;
             """
     )
     Integer deleteVenueById(Integer venueId);
     @Select(
     """
     update venues set venue_name = #{venueRequest.venueName} ,  location = #{venueRequest.location}
     where venue_id = #{venueId}
     returning * ;
     """
     )
     @ResultMap("VenuesMapper")
      Venues updateVenueById(Integer venueId , @Param("venueRequest") VenueRequest venueRequest);
}
