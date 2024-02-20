package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscriptionStatusChangedByMember extends AbstractEvent {

    private Long id;
    private Long usrId;
    private String usrNm;
    private Long prdId;
    private String prdStatus;
    private String prdNm;

    public SubscriptionStatusChangedByMember(Member aggregate) {
        super(aggregate);
    }

    public SubscriptionStatusChangedByMember() {
        super();
    }
}
//>>> DDD / Domain Event
