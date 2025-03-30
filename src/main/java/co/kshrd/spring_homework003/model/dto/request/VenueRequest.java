package co.kshrd.spring_homework003.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueRequest {
    @NotBlank(message = "VenueName is not nul")
    private String venueName;
    @NotBlank(message = "location is not nul")
    private String location ;
}
