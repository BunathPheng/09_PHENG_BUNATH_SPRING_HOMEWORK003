package co.kshrd.spring_homework003.controller;

import co.kshrd.spring_homework003.model.dto.request.AttendeesRequest;
import co.kshrd.spring_homework003.model.dto.respone.ApiResponse;
import co.kshrd.spring_homework003.model.entity.Attendee;
import co.kshrd.spring_homework003.service.ServiceAttendee;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    private final ServiceAttendee serviceAttendee;

    @GetMapping
    @Operation(summary = "Get All Attendee")
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendee(
            @Positive @RequestParam(defaultValue = "1") Integer offset,
            @Positive @RequestParam(defaultValue = "4") Integer limit
    ) {
        List<Attendee> attendees = serviceAttendee.getAllAttendee(offset, limit);
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>
                        builder()
                .message("All attendees have been successfully fetched.")
                .payload(attendees)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{attendee-id}")
    @Operation(summary = "Get Attendee By Id")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") @Positive Integer attendeeId) {
        Attendee attendee = serviceAttendee.getAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("The attendee has been successfully founded.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Add a new attendee")
    public ResponseEntity<ApiResponse<Attendee>> insertAttendee(@Valid @RequestBody AttendeesRequest attendeeRequest) {
        Attendee attendee = serviceAttendee.insertAttendeeTo(attendeeRequest);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("The attendee has been successfully added.")
                .payload(attendee)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(
            @PathVariable("attendee-id") @Positive Integer attendeeId,
             @RequestBody @Valid AttendeesRequest attendeeRequest) {
        {
            Attendee attendee = serviceAttendee.updateAttendeeById(attendeeId, attendeeRequest);
            ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                    .message("The attendee has been successfully updated.")
                    .payload(attendee)
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("{attendee_id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteAttendeeById (@PathVariable("attendee_id") @Positive Integer attendeeId){
        Boolean attendee = serviceAttendee.deleteAttendeeById(attendeeId);
        ApiResponse<Boolean> response = ApiResponse.<Boolean>
                builder()
                .message("Attendee deleted is successfully")
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
}
