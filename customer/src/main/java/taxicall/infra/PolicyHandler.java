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

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Accumulationed'"
    )
    public void wheneverAccumulationed_UserNotification(
        @Payload Accumulationed accumulationed
    ) {
        Accumulationed event = accumulationed;
        System.out.println(
            "\n\n##### listener UserNotification : " + accumulationed + "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Departured'"
    )
    public void wheneverDepartured_UserNotification(
        @Payload Departured departured
    ) {
        Departured event = departured;
        System.out.println(
            "\n\n##### listener UserNotification : " + departured + "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Dispatched'"
    )
    public void wheneverDispatched_UserNotification(
        @Payload Dispatched dispatched
    ) {
        Dispatched event = dispatched;
        System.out.println(
            "\n\n##### listener UserNotification : " + dispatched + "\n\n"
        );
        // Sample Logic //

    }
}
