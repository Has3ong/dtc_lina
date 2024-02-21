package airplane.domain;

import airplane.AirplaneApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Airplane_table")
@Data
//<<< DDD / Aggregate Root
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long airplaneId;

    private Long reservId;

    private String reservStatus;

    private Integer seatQty;

    public static AirplaneRepository repository() {
        AirplaneRepository airplaneRepository = AirplaneApplication.applicationContext.getBean(
            AirplaneRepository.class
        );
        return airplaneRepository;
    }
}
//>>> DDD / Aggregate Root
