package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import untitled.infra.AbstractEvent;

@Data
public class SubscriptionApplicationCompleted extends AbstractEvent {

    private Long insuranceId;
    private String prdStatus;
    private String prdNm;
}
