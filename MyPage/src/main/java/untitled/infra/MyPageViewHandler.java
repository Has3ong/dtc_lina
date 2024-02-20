package untitled.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import untitled.config.kafka.KafkaProcessor;
import untitled.domain.*;

@Service
public class MyPageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenInsurancePremiumPaid_then_CREATE_1(
        @Payload InsurancePremiumPaid insurancePremiumPaid
    ) {
        try {
            if (!insurancePremiumPaid.validate()) return;

            // view 객체 생성
            MyPage myPage = new MyPage();
            // view 객체에 이벤트의 Value 를 set 함
            myPage.setInsuranceId(insurancePremiumPaid.getInsuranceId());
            myPage.setUsrId(insurancePremiumPaid.getPrdId());
            myPage.setPrdId(insurancePremiumPaid.getPrdId());
            // view 레파지 토리에 save
            myPageRepository.save(myPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSubscriptionApplicationCompleted_then_UPDATE_1(
        @Payload SubscriptionApplicationCompleted subscriptionApplicationCompleted
    ) {
        try {
            if (!subscriptionApplicationCompleted.validate()) return;
            // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findByInsuranceId(
                subscriptionApplicationCompleted.getInsuranceId()
            );

            if (myPageOptional.isPresent()) {
                MyPage myPage = myPageOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myPage.setUsrNm(subscriptionApplicationCompleted.getUsrNm());
                myPage.setPrdNm(subscriptionApplicationCompleted.getPrdNm());
                myPage.setPrdStatus(결제완료);
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenInsuranceReviewApproved_then_UPDATE_2(
        @Payload InsuranceReviewApproved insuranceReviewApproved
    ) {
        try {
            if (!insuranceReviewApproved.validate()) return;
            // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findByInsuranceId(
                insuranceReviewApproved.getInsuranceId()
            );

            if (myPageOptional.isPresent()) {
                MyPage myPage = myPageOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myPage.setPrdStatus(승인완료);
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
