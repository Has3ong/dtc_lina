package airplane.infra;

import airplane.config.kafka.KafkaProcessor;
import airplane.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ReservationRepository reservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationApproved'"
    )
    public void ReservationApproved_ReservationUpdated(
        @Payload Reservation reservation
    ) {
        reservationRepository.save(reservation);
	}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationRejected'"
    )
    public void ReservationRejected_ReservationUpdated(
        @Payload Reservation reservation
    ) {
        reservationRepository.deleteByReservId(reservation.getReservId());
        ReservationCanceled reservationCanceled = new ReservationCanceled(reservation);
        reservationCanceled.publishAfterCommit();
	}
}
//>>> Clean Arch / Inbound Adaptor
