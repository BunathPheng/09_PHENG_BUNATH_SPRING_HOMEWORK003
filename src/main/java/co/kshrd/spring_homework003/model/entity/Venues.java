package co.kshrd.spring_homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venues {
    private Integer venueId;
    private String  venueName;
    private String  location ;
}
