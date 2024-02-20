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

    private Long insuranceId;
    private String prdStatus;
    private String prdNm;

    public SubscriptionApplicationCompleted(Member aggregate) {
        super(aggregate);
    }

    public SubscriptionApplicationCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
