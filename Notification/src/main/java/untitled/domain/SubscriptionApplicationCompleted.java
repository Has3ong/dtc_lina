package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class SubscriptionApplicationCompleted extends AbstractEvent {

    private Long insuranceId;
    private String prdStatus;
    private String prdNm;
}