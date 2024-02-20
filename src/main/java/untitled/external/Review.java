package untitled.external;

import java.util.Date;
import lombok.Data;

@Data
public class Review {

    private Long id;
    private Long prdId;
    private Long usrId;
    private String prdStatus;
    private String prdNm;
}
