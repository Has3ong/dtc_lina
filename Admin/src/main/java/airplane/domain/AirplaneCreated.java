package airplane.domain;

import airplane.domain.*;
import airplane.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class AirplaneCreated extends AbstractEvent {

    private Long airPlaneId;
    private Integer seatQty;

    public AirplaneCreated(Admin aggregate) {
        super(aggregate);
    }

    public AirplaneCreated() {
        super();
    }
}
//>>> DDD / Domain Event
