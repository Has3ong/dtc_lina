package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.MemberApplication;
import untitled.domain.SubscriptionApplicationCompleted;
import untitled.domain.SubscriptionCanceled;

@Entity
@Table(name = "Member_table")
@Data
//<<< DDD / Aggregate Root
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long insuranceId;

    private Long usrId;

    private String usrNm;

    private Long prdId;

    private String prdStatus;

    private String prdNm;

    @PostUpdate
    public void onPostUpdate() {
        SubscriptionCanceled subscriptionCanceled = new SubscriptionCanceled(
            this
        );
        subscriptionCanceled.publishAfterCommit();

        SubscriptionApplicationCompleted subscriptionApplicationCompleted = new SubscriptionApplicationCompleted(
            this
        );
        subscriptionApplicationCompleted.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static MemberRepository repository() {
        MemberRepository memberRepository = MemberApplication.applicationContext.getBean(
            MemberRepository.class
        );
        return memberRepository;
    }
}
//>>> DDD / Aggregate Root
