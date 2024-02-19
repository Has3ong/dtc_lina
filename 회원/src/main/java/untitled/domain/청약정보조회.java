package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "청약정보조회_table")
@Data
public class 청약정보조회 {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
}
