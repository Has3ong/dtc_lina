package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.domain.보험료결제됨;
import untitled.보험Application;

@Entity
@Table(name = "보험_table")
@Data
//<<< DDD / Aggregate Root
public class 보험 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String prdNm;

    private Long usrId;

    @PostPersist
    public void onPostPersist() {
        보험료결제됨 보험료결제됨 = new 보험료결제됨(this);
        보험료결제됨.publishAfterCommit();
    }

    public static 보험Repository repository() {
        보험Repository 보험Repository = 보험Application.applicationContext.getBean(
            보험Repository.class
        );
        return 보험Repository;
    }
}
//>>> DDD / Aggregate Root
