package v2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class TripRequest {
    private String id;
    private Candidate candidate;
    private PickupPoint pickupPoint;
    private DropPoint dropPoint;
    private TimeWindow pickupTimeWindow;
    private TimeWindow dropTimeWindow;
}
