package airplane.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "GetReservationInfo_table")
@Data
public class GetReservationInfo {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long reservId;

    private Long airPlaneId;
    private String reservStatus;
}
