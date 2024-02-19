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
public class 청약정보조회ViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private 청약정보조회Repository 청약정보조회Repository;
    //>>> DDD / CQRS
}
