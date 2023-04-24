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
    CallRepository callRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Dispatched'"
    )
    public void wheneverDispatched_UpdateTaxiInfo(
        @Payload Dispatched dispatched
    ) {
        Dispatched event = dispatched;
        System.out.println(
            "\n\n##### listener UpdateTaxiInfo : " + dispatched + "\n\n"
        );

        // Sample Logic //
        Call.updateTaxiInfo(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Departured'"
    )
    public void wheneverDepartured_UpdateStatus(
        @Payload Departured departured
    ) {
        Departured event = departured;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + departured + "\n\n"
        );

        // Sample Logic //
        Call.updateStatus(event);
    }
}
