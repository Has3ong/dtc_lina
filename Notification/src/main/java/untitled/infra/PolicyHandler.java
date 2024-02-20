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
    NotificationRepository notificationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SubscriptionCanceled'"
    )
    public void wheneverSubscriptionCanceled_GetNotificationByChangeStatus(
        @Payload SubscriptionCanceled subscriptionCanceled
    ) {
        SubscriptionCanceled event = subscriptionCanceled;
        System.out.println(
            "\n\n##### listener GetNotificationByChangeStatus : " +
            subscriptionCanceled +
            "\n\n"
        );

        // Sample Logic //
        Notification.getNotificationByChangeStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SubscriptionApplicationCompleted'"
    )
    public void wheneverSubscriptionApplicationCompleted_GetNotificationByChangeStatus(
        @Payload SubscriptionApplicationCompleted subscriptionApplicationCompleted
    ) {
        SubscriptionApplicationCompleted event =
            subscriptionApplicationCompleted;
        System.out.println(
            "\n\n##### listener GetNotificationByChangeStatus : " +
            subscriptionApplicationCompleted +
            "\n\n"
        );

        // Sample Logic //
        Notification.getNotificationByChangeStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='InsuranceReviewApproved'"
    )
    public void wheneverInsuranceReviewApproved_GetNotificationByChangeStatus(
        @Payload InsuranceReviewApproved insuranceReviewApproved
    ) {
        InsuranceReviewApproved event = insuranceReviewApproved;
        System.out.println(
            "\n\n##### listener GetNotificationByChangeStatus : " +
            insuranceReviewApproved +
            "\n\n"
        );

        // Sample Logic //
        Notification.getNotificationByChangeStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='InsuranceReviewCanceled'"
    )
    public void wheneverInsuranceReviewCanceled_GetNotificationByChangeStatus(
        @Payload InsuranceReviewCanceled insuranceReviewCanceled
    ) {
        InsuranceReviewCanceled event = insuranceReviewCanceled;
        System.out.println(
            "\n\n##### listener GetNotificationByChangeStatus : " +
            insuranceReviewCanceled +
            "\n\n"
        );

        // Sample Logic //
        Notification.getNotificationByChangeStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
