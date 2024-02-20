package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class InsuranceReviewCanceled extends AbstractEvent {

    private Long insuranceId;
    private Long prdId;
    private Long usrId;
    private String prdStatus;

    public InsuranceReviewCanceled(Review aggregate) {
        super(aggregate);
    }

    public InsuranceReviewCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
