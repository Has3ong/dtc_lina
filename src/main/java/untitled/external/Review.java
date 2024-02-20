package untitled.external;

import java.util.Date;
import lombok.Data;

@Data
public class Review {

    private Long insuranceId;
    private Long prdId;
    private Long usrId;
    private String prdStatus;
    private String prdNm;
}
