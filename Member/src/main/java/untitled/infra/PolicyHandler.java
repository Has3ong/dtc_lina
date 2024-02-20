package untitled.infra;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import org.springframework.stereotype.Service;

import untitled.config.kafka.KafkaProcessor;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    MemberRepository memberRepository;

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='InsurancePremiumPaid'"
    )
    public void InsurancePremiumPaid_SubscriptionApplicationComplete(
        @Payload Member member
    ) {
        Member event = member;
        System.out.println("\n\n==================================================");
        System.out.println("##### listener IncreaseStock : " + member + " / EventInfo : " + event + "\n\n");

        memberRepository.save(member);
        System.out.println("\n\n==================================================");
        System.out.println("##### save Repository Information : " + memberRepository.findById(member.getId()));

        member.setPrdStatus("결제완료");
        SubscriptionApplicationCompleted subscriptionApplicationCompleted = new SubscriptionApplicationCompleted(member);
        subscriptionApplicationCompleted.publishAfterCommit();
    }
}
//>>> Clean Arch / Inbound Adaptor
