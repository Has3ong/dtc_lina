package airplane.domain;

import airplane.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ReservationCompleted extends AbstractEvent {

    private Long reservId;
    private Long airPlaneId;
}
