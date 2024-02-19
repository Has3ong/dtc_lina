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
public class 보험조회ViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private 보험조회Repository 보험조회Repository;
    //>>> DDD / CQRS
}
