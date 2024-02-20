package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import untitled.infra.AbstractEvent;

@Data
public class InsurancePremiumPaid extends AbstractEvent {

    private Long insuranceId;
    private Long usrId;
    private Long prdId;
}
