package co.kshrd.spring_homework003.service;

import co.kshrd.spring_homework003.model.dto.request.VenueRequest;
import co.kshrd.spring_homework003.model.dto.respone.ApiResponse;
import co.kshrd.spring_homework003.model.entity.Venues;

import java.util.List;

public interface ServiceVenue {
    List<Venues> getAllVenues(Integer page ,Integer size);
    Venues getVenueById(Integer venueId);
    Venues insertVenue(VenueRequest venueRequest);
    Boolean deleteVenueById(Integer venueId);
    Venues updateVenueById(Integer venueId ,VenueRequest venueRequest) ;
}
