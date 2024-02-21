package airplane.infra;

import airplane.config.kafka.KafkaProcessor;
import airplane.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	AirplaneRepository airplaneRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationCompleted'"
    )
    public void ReservationCompleted_ReservationApproved(
        @Payload Airplane airplane
    ) {
    	
    	Airplane airplaneStatus = airplaneRepository.findByAirplaneId(airplane.getAirplaneId()).get();
    	
        if (null == airplaneStatus || airplaneStatus.getSeatQty() == 0) {
        	airplane.setReservStatus("취소");
            ReservationRejected reservationRejected = new ReservationRejected(airplane);
            reservationRejected.publishAfterCommit();
        } else {
        	airplane.setReservStatus("승인");
            ReservationApproved reservationApproved = new ReservationApproved(airplane);
            reservationApproved.publishAfterCommit();
        }
    }
    
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AirplaneCreated'"
    )
    public void AirplaneCreated_ReservationApproved(
        @Payload Airplane airplane
    ) {
		airplaneRepository.save(airplane);
	}
}
//>>> Clean Arch / Inbound Adaptor
