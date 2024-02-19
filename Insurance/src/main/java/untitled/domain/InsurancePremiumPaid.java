package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class InsurancePremiumPaid extends AbstractEvent {

    private Long id;
    private String prdNm;
    private Long usrId;

    public InsurancePremiumPaid(Insurance aggregate) {
        super(aggregate);
    }

    public InsurancePremiumPaid() {
        super();
    }
}
//>>> DDD / Domain Event