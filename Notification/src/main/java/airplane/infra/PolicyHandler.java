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

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationCompleted'"
    )
    public void wheneverReservationCompleted_NotifiedUser(
        @Payload ReservationCompleted reservationCompleted
    ) {
        ReservationCompleted event = reservationCompleted;
        System.out.println(
            "\n\n##### listener NotifiedUser : " + reservationCompleted + "\n\n"
        );

        // Sample Logic //
        Notification.notifiedUser(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationCanceled'"
    )
    public void wheneverReservationCanceled_NotifiedUser(
        @Payload ReservationCanceled reservationCanceled
    ) {
        ReservationCanceled event = reservationCanceled;
        System.out.println(
            "\n\n##### listener NotifiedUser : " + reservationCanceled + "\n\n"
        );

        // Sample Logic //
        Notification.notifiedUser(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationUpdated'"
    )
    public void wheneverReservationUpdated_NotifiedUser(
        @Payload ReservationUpdated reservationUpdated
    ) {
        ReservationUpdated event = reservationUpdated;
        System.out.println(
            "\n\n##### listener NotifiedUser : " + reservationUpdated + "\n\n"
        );

        // Sample Logic //
        Notification.notifiedUser(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
