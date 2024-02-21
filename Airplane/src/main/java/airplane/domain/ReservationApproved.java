package airplane.domain;

import airplane.domain.*;
import airplane.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReservationApproved extends AbstractEvent {

    private Long airPlaneId;
    private Long reservId;
    private String reservStatus;

    public ReservationApproved(Airplane aggregate) {
        super(aggregate);
    }

    public ReservationApproved() {
        super();
    }
}
//>>> DDD / Domain Event
