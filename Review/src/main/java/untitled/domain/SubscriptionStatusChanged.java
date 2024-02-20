package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscriptionStatusChanged extends AbstractEvent {

    private Long id;
    private Long prdId;
    private Long usrId;
    private String prdStatus;

    public SubscriptionStatusChanged(Review aggregate) {
        super(aggregate);
    }

    public SubscriptionStatusChanged() {
        super();
    }
}
//>>> DDD / Domain Event
