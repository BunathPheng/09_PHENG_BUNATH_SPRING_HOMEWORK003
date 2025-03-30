package co.kshrd.spring_homework003.service.serviceImpl;

import co.kshrd.spring_homework003.exception.NotFoundExceptionHandler;
import co.kshrd.spring_homework003.model.dto.request.VenueRequest;
import co.kshrd.spring_homework003.model.entity.Venues;
import co.kshrd.spring_homework003.repository.VenueRepository;
import co.kshrd.spring_homework003.service.ServiceVenue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceVenueImpl implements ServiceVenue {
    private  final VenueRepository  venueRepository;
    @Override
    public List<Venues> getAllVenues(Integer page , Integer size) {
        List<Venues> venues = venueRepository.getAllVenues(page , size);
        if (venues == null)
        {
            throw  new NotFoundExceptionHandler("Data in DataBase is null");
        }
        return venues;
    }

    @Override
    public Venues getVenueById(Integer venueId) {
        Venues venues = venueRepository.findVenueById(venueId);
        if(venues == null)
        {
            throw new NotFoundExceptionHandler("Venues not found" + venueId );
        }
        return venues;
    }

    @Override
    public Venues insertVenue(VenueRequest venueRequest) {
        Venues venues = venueRepository.insertVenue(venueRequest);
        if(venues == null)
        {
            throw  new NotFoundExceptionHandler("Create is not successfully");
        }
        return venues;
    }

    @Override
    public Boolean deleteVenueById(Integer venueId) {
        Integer deletedId = venueRepository.deleteVenueById(venueId);
        if (deletedId == null) {
            throw new NotFoundExceptionHandler("Venue not found" + venueId);
        }
        return true;
    }

    @Override
    public Venues updateVenueById(Integer venueId , VenueRequest venueRequest) {
        Venues  venues = venueRepository.updateVenueById(venueId ,venueRequest);
        if(venues == null){
            throw  new NotFoundExceptionHandler("Venue is not found with id:" + venueId);
        }
        return venues;
    }

}
