package airplane.domain;

import airplane.domain.*;
import airplane.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReservationCompleted extends AbstractEvent {

    private Long reservId;
    private Long airPlaneId;

    public ReservationCompleted(Reservation aggregate) {
        super(aggregate);
    }

    public ReservationCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
