package airplane.external;

import java.util.Date;
import lombok.Data;

@Data
public class Reservation {

    private Long reservId;
    private Long airPlaneId;
    private String reservStatus;
}
