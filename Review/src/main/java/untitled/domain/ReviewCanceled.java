package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReviewCanceled extends AbstractEvent {

    private Long id;
    private Long prdId;
    private Long usrId;
    private String prdStatus;

    public ReviewCanceled(Review aggregate) {
        super(aggregate);
    }

    public ReviewCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
