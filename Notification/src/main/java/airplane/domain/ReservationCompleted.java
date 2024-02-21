package airplane.domain;

import airplane.domain.*;
import airplane.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReservationCompleted extends AbstractEvent {

    private Long reservId;
    private Long airPlaneId;
}
