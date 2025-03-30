package co.kshrd.spring_homework003.controller;

import co.kshrd.spring_homework003.model.dto.request.EventRequest;
import co.kshrd.spring_homework003.model.dto.respone.ApiResponse;
import co.kshrd.spring_homework003.model.entity.Event;
import co.kshrd.spring_homework003.service.serviceImpl.ServiceEventImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private  final ServiceEventImpl serviceEventImpl;
    @GetMapping
    @Operation(summary = "Get all events")
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvent(
            @Positive @RequestParam(defaultValue = "1") Integer offset,
            @Positive @RequestParam(defaultValue = "4") Integer limit
    ){
        List<Event> events = serviceEventImpl.getAllEvent(offset , limit);
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .message("All events have been successfully fetched.")
                .payload(events)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
    @GetMapping("{event-id}")
    @Operation(summary = "Get all event by id")
    public ResponseEntity<ApiResponse<Event>> getEventById( @Positive @PathVariable("event-id") Integer eventId)
    {
        Event event = serviceEventImpl.getAllEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Get all event by Id")
                .payload(event)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
    @DeleteMapping("{eventId}")
    @Operation(summary = "Delete event by id")
    public ResponseEntity<ApiResponse<Boolean>> deleteEventById(@Positive @PathVariable Integer eventId){
        Boolean event  = serviceEventImpl.deleteEventById(eventId);
        ApiResponse<Boolean> response = ApiResponse.<Boolean>builder()
                .message("delete event is successfully")
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(response , HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Event>> insertEvent(@RequestBody @Valid EventRequest eventRequest)
    {
         Event event  = serviceEventImpl.insertEvent(eventRequest);
         ApiResponse<Event> response = ApiResponse.<Event>
                  builder()
                 .message("Event is created")
                 .payload(event)
                 .status(HttpStatus.CREATED)
                 .build();
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @PutMapping("{attendee_id}")
    public ResponseEntity<ApiResponse<Event>> updateEventById(@PathVariable("attendee_id") @Positive Integer eventId , @RequestBody EventRequest eventRequest){
        Event event =  serviceEventImpl.updateEventById(eventId , eventRequest);
        ApiResponse<Event> response = ApiResponse.<Event>
                 builder()
                .message("Update is successfully")
                .payload(event)
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
}
