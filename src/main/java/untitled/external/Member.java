package untitled.external;

import java.util.Date;
import lombok.Data;

@Data
public class Member {

    private Long id;
    private Long usrId;
    private String usrNm;
    private Long prdId;
    private String prdStatus;
    private String prdNm;
}