package airplane.domain;

import airplane.domain.*;
import airplane.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReservationUpdated extends AbstractEvent {

    private Long reservId;
    private Long airPlaneId;
    private String reservStatus;

    public ReservationUpdated(Reservation aggregate) {
        super(aggregate);
    }

    public ReservationUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
