package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class 보험료결제됨 extends AbstractEvent {

    private Long id;
    private String prdNm;
    private Long usrId;

    public 보험료결제됨(보험 aggregate) {
        super(aggregate);
    }

    public 보험료결제됨() {
        super();
    }
}
//>>> DDD / Domain Event
