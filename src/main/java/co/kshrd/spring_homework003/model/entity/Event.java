package co.kshrd.spring_homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    private Integer eventId;
    private String  eventName;
    private LocalDateTime Date;
    private Venues venue;
    private List<Attendee> attendees;
}
