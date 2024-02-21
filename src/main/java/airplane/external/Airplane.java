package airplane.external;

import java.util.Date;
import lombok.Data;

@Data
public class Airplane {

    private Long id;
    private Long reservId;
    private String reservStatus;
    private Integer seatQty;
}
