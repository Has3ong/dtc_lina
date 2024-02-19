package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.InsuranceApplication;
import untitled.domain.InsurancePremiumPaid;

@Entity
@Table(name = "Insurance_table")
@Data
//<<< DDD / Aggregate Root
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String prdNm;

    private Long usrId;

    @PostPersist
    public void onPostPersist() {
        InsurancePremiumPaid insurancePremiumPaid = new InsurancePremiumPaid(
            this
        );
        insurancePremiumPaid.publishAfterCommit();
    }

    public static InsuranceRepository repository() {
        InsuranceRepository insuranceRepository = InsuranceApplication.applicationContext.getBean(
            InsuranceRepository.class
        );
        return insuranceRepository;
    }
}
//>>> DDD / Aggregate Root
