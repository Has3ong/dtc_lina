package airplane.domain;

import airplane.domain.*;
import airplane.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReservationRejected extends AbstractEvent {

    private Long airPlaneId;
    private Long reservId;
    private String reservStatus;

    public ReservationRejected(Airplane aggregate) {
        super(aggregate);
    }

    public ReservationRejected() {
        super();
    }
}
//>>> DDD / Domain Event
