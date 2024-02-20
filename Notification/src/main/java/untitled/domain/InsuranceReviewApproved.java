package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class InsuranceReviewApproved extends AbstractEvent {

    private Long insuranceId;
    private Long prdId;
    private Long usrId;
    private String prdStatus;
}
