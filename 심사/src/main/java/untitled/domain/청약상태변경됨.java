package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class 청약상태변경됨 extends AbstractEvent {

    private Long id;
    private Long prdId;
    private Long usrId;
    private String prdStatus;

    public 청약상태변경됨(심사 aggregate) {
        super(aggregate);
    }

    public 청약상태변경됨() {
        super();
    }
}
//>>> DDD / Domain Event
