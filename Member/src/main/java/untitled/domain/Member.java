package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.MemberApplication;
import untitled.domain.InsurancePremiumPaid;
import untitled.domain.SubscriptionStatusChanged;

@Entity
@Table(name = "Member_table")
@Data
//<<< DDD / Aggregate Root
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long usrId;

    private String usrNm;

    private Long prdId;

    private String prdStatus;

    private String prdNm;

    @PreUpdate
    public void onPreUpdate() {
        SubscriptionStatusChanged subscriptionStatusChanged = new SubscriptionStatusChanged(
            this
        );
        subscriptionStatusChanged.publishAfterCommit();

        InsurancePremiumPaid insurancePremiumPaid = new InsurancePremiumPaid(
            this
        );
        insurancePremiumPaid.publishAfterCommit();
    }

    public static MemberRepository repository() {
        MemberRepository memberRepository = MemberApplication.applicationContext.getBean(
            MemberRepository.class
        );
        return memberRepository;
    }
}
//>>> DDD / Aggregate Root
