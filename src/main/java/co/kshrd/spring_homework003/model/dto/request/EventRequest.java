package co.kshrd.spring_homework003.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class EventRequest {
    @NotBlank(message = "Event name is invalid")
    private String eventName;
    private LocalDateTime eventDate;
    @NotNull(message = "Venue ID is required")
    private Integer venuesId;
    private List<Integer> attendeeId;
}
