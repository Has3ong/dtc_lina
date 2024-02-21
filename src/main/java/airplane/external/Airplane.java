package airplane.external;

import java.util.Date;
import lombok.Data;

@Data
public class Airplane {

    private Long airplaneId;
    private Integer seatQty;
    private Long reservId;
    private String reservStatus;
}
