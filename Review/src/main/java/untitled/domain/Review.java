package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.ReviewApplication;
import untitled.domain.ReviewApproved;
import untitled.domain.ReviewCanceled;
import untitled.domain.SubscriptionStatusChanged;

@Entity
@Table(name = "Review_table")
@Data
//<<< DDD / Aggregate Root
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long prdId;

    private Long usrId;

    private String prdStatus;

    private String prdNm;

    @PostPersist
    public void onPostPersist() {
        ReviewApproved reviewApproved = new ReviewApproved(this);
        reviewApproved.publishAfterCommit();

        ReviewCanceled reviewCanceled = new ReviewCanceled(this);
        reviewCanceled.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        SubscriptionStatusChanged subscriptionStatusChanged = new SubscriptionStatusChanged(
            this
        );
        subscriptionStatusChanged.publishAfterCommit();
    }

    public static ReviewRepository repository() {
        ReviewRepository reviewRepository = ReviewApplication.applicationContext.getBean(
            ReviewRepository.class
        );
        return reviewRepository;
    }
}
//>>> DDD / Aggregate Root
