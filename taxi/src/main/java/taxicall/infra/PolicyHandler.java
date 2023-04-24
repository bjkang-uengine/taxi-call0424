package taxicall.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import taxicall.config.kafka.KafkaProcessor;
import taxicall.domain.*;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    TaxiCallRepository taxiCallRepository;

    @Autowired
    CommentRepository commentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Called'"
    )
    public void wheneverCalled_DuplicateCall(@Payload Called called) {
        Called event = called;
        System.out.println(
            "\n\n##### listener DuplicateCall : " + called + "\n\n"
        );

        // Sample Logic //
        TaxiCall.duplicateCall(event);
    }
}
