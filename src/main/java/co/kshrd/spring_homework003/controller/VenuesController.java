package co.kshrd.spring_homework003.controller;

import co.kshrd.spring_homework003.model.dto.request.VenueRequest;
import co.kshrd.spring_homework003.model.dto.respone.ApiResponse;
import co.kshrd.spring_homework003.model.entity.Venues;
import co.kshrd.spring_homework003.service.serviceImpl.ServiceVenueImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/v1/venues")
@RequiredArgsConstructor
public class VenuesController {
    private final ServiceVenueImpl serviceVenue;
    @GetMapping
    @Operation(summary = "Get all venues")
    public ResponseEntity<ApiResponse<List<Venues>>> getAllVenues (
            @Positive  @RequestParam (defaultValue = "1") Integer offset ,
            @Positive  @RequestParam (defaultValue = "2") Integer limit
    ) {
              List<Venues> venues = serviceVenue.getAllVenues(offset ,limit);
              ApiResponse<List<Venues>> data =ApiResponse.<List<Venues>>builder()
                .message("All students have been successfully fetched")
                .payload(venues)
                .status(HttpStatus.OK)
                .build();
              return new ResponseEntity<>(data , HttpStatus.OK) ;
    }
    //Get Venue By Id
    @GetMapping("{venue-id}")
    @Operation(summary = "Get Venues By Id")
    public ResponseEntity<ApiResponse<Venues>> getVenueById(@PathVariable("venue-id") @Positive Integer id){
        Venues venue = serviceVenue.getVenueById(id);
        ApiResponse<Venues>response  = ApiResponse.<Venues>builder()
                .message("All Venues have been successfully fetched")
                .payload(venue)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
    //Post Venues
    @PostMapping()
    @Operation(summary = "Add a new Venue")
    public ResponseEntity<ApiResponse<Venues>> insertVenue(@RequestBody @Valid VenueRequest venueRequest){
        Venues venue = serviceVenue.insertVenue(venueRequest);
        ApiResponse<Venues>response  = ApiResponse.<Venues>builder()
                .message("The venue has been successfully added.")
                .payload(venue)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }
    @DeleteMapping("{venue-id}")
    @Operation(summary = "Delete by venue id")
    public ResponseEntity<ApiResponse<Boolean>> deleteVenueById(@PathVariable("venue-id") @Positive Integer venueId)
    {
        Boolean venue = serviceVenue.deleteVenueById(venueId);
        ApiResponse<Boolean> response  = ApiResponse.<Boolean>builder()
                .message("The venue has been successfully added.")
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PutMapping("{venue-id}")
    @Operation(summary = "Update venue by id")
    public ResponseEntity<ApiResponse<Venues>> updateVenueById(@PathVariable("venue-id") @Positive Integer venueId , @RequestBody @Valid VenueRequest venueRequest){
        Venues venues = serviceVenue.updateVenueById(venueId , venueRequest);
        ApiResponse<Venues> respone = ApiResponse.<Venues>builder()
                .message("The venue has been successfully updated.")
                .payload(venues)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(respone , HttpStatus.OK);
    }



}
