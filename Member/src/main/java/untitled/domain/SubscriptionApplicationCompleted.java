package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscriptionApplicationCompleted extends AbstractEvent {

    private Long id;
    private Long prdId;
    private String prdStatus;
    private Long usrId;

    public SubscriptionApplicationCompleted(Member aggregate) {
        super(aggregate);
    }

    public SubscriptionApplicationCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
