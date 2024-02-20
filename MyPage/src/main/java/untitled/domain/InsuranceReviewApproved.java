package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import untitled.infra.AbstractEvent;

@Data
public class InsuranceReviewApproved extends AbstractEvent {

    private Long insuranceId;
    private Long prdId;
    private Long usrId;
    private String prdStatus;
}
