package airplane.domain;

import airplane.ReservationApplication;
import airplane.domain.ReservationCanceled;
import airplane.domain.ReservationCompleted;
import airplane.domain.ReservationUpdated;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Reservation_table")
@Data
//<<< DDD / Aggregate Root
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservId;

    private Long airPlaneId;

    private String reservStatus;

    @PostPersist
    public void onPostPersist() {
        ReservationCompleted reservationCompleted = new ReservationCompleted(
            this
        );
        reservationCompleted.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        ReservationCanceled reservationCanceled = new ReservationCanceled(this);
        reservationCanceled.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        ReservationUpdated reservationUpdated = new ReservationUpdated(this);
        reservationUpdated.publishAfterCommit();
    }

    public static ReservationRepository repository() {
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(
            ReservationRepository.class
        );
        return reservationRepository;
    }
}
//>>> DDD / Aggregate Root
