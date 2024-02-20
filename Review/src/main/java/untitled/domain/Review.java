package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.ReviewApplication;
import untitled.domain.InsuranceReviewApproved;
import untitled.domain.InsuranceReviewCanceled;

@Entity
@Table(name = "Review_table")
@Data
//<<< DDD / Aggregate Root
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long insuranceId;

    private Long prdId;

    private Long usrId;

    private String prdStatus;

    private String prdNm;

    @PostPersist
    public void onPostPersist() {
        InsuranceReviewApproved insuranceReviewApproved = new InsuranceReviewApproved(
            this
        );
        insuranceReviewApproved.publishAfterCommit();

        InsuranceReviewCanceled insuranceReviewCanceled = new InsuranceReviewCanceled(
            this
        );
        insuranceReviewCanceled.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static ReviewRepository repository() {
        ReviewRepository reviewRepository = ReviewApplication.applicationContext.getBean(
            ReviewRepository.class
        );
        return reviewRepository;
    }
}
//>>> DDD / Aggregate Root
