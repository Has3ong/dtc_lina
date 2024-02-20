package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class InsuranceReviewApproved extends AbstractEvent {

    private Long id;
    private Long prdId;
    private Long usrId;
    private String prdStatus;

    public InsuranceReviewApproved(Review aggregate) {
        super(aggregate);
    }

    public InsuranceReviewApproved() {
        super();
    }
}
//>>> DDD / Domain Event
