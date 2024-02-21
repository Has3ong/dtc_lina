package airplane.domain;

import airplane.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ReservationCanceled extends AbstractEvent {

    private Long reservId;
    private Long airPlaneId;
    private String reservStatus;
}
