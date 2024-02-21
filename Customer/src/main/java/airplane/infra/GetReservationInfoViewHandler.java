package airplane.infra;

import airplane.config.kafka.KafkaProcessor;
import airplane.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class GetReservationInfoViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private GetReservationInfoRepository getReservationInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCompleted_then_CREATE_1(
        @Payload ReservationCompleted reservationCompleted
    ) {
        try {
            if (!reservationCompleted.validate()) return;

            // view 객체 생성
            GetReservationInfo getReservationInfo = new GetReservationInfo();
            // view 객체에 이벤트의 Value 를 set 함
            getReservationInfo.setReservId(reservationCompleted.getReservId());
            getReservationInfo.setReservStatus("접수완료");
            getReservationInfo.setAirPlaneId(
                reservationCompleted.getAirPlaneId()
            );
            // view 레파지 토리에 save
            getReservationInfoRepository.save(getReservationInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationUpdated_then_UPDATE_1(
        @Payload ReservationUpdated reservationUpdated
    ) {
        try {
            if (!reservationUpdated.validate()) return;
            // view 객체 조회
            Optional<GetReservationInfo> getReservationInfoOptional = getReservationInfoRepository.findByReservId(
                reservationUpdated.getReservId()
            );

            if (getReservationInfoOptional.isPresent()) {
                GetReservationInfo getReservationInfo = getReservationInfoOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                getReservationInfo.setReservStatus(
                    reservationUpdated.getReservStatus()
                );
                // view 레파지 토리에 save
                getReservationInfoRepository.save(getReservationInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCanceled_then_UPDATE_2(
        @Payload ReservationCanceled reservationCanceled
    ) {
        try {
            if (!reservationCanceled.validate()) return;
            // view 객체 조회
            Optional<GetReservationInfo> getReservationInfoOptional = getReservationInfoRepository.findByReservId(
                reservationCanceled.getReservId()
            );

            if (getReservationInfoOptional.isPresent()) {
                GetReservationInfo getReservationInfo = getReservationInfoOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                getReservationInfo.setReservStatus(
                    reservationCanceled.getReservStatus()
                );
                // view 레파지 토리에 save
                getReservationInfoRepository.save(getReservationInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
