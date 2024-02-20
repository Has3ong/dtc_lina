package untitled.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
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
    public void InsurancePremiumPaid_createInsurance(@Payload InsurancePremiumPaid insurancePremiumPaid) {
        InsurancePremiumPaid event = insurancePremiumPaid;
        System.out.println("\n\n==================================================");
        System.out.println("##### listener IncreaseStock : " + insurancePremiumPaid + " / EventInfo : " + event + "\n\n");
    }
}
//>>> Clean Arch / Inbound Adaptor
