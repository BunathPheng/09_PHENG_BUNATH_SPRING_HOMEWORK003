package co.kshrd.spring_homework003.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeesRequest {
    @NotBlank(message = "Attendee name is required")
    private String attendeeName;
    @Email(message = "Is required email format")
    private String email;
}
