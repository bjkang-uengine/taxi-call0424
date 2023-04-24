package taxicall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import taxicall.TaxiApplication;

@Entity
@Table(name = "TaxiCall_table")
@Data
public class TaxiCall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    private String model;

    private String driverName;

    private String driverMobileNumber;

    private Long callId;

    private String status;

    private String userId;

    private Long pay;

    @PostPersist
    public void onPostPersist() {}

    public static TaxiCallRepository repository() {
        TaxiCallRepository taxiCallRepository = TaxiApplication.applicationContext.getBean(
            TaxiCallRepository.class
        );
        return taxiCallRepository;
    }

    public void boarding(BoardingCommand boardingCommand) {
        Boardinged boardinged = new Boardinged(this);
        boardinged.publishAfterCommit();
    }

    public void arrival(ArrivalCommand arrivalCommand) {
        Arrivaled arrivaled = new Arrivaled(this);
        arrivaled.publishAfterCommit();
    }

    public void paymentRequest(PaymentRequestCommand paymentRequestCommand) {
        Paymented paymented = new Paymented(this);
        paymented.publishAfterCommit();
    }

    public void accept(AcceptCommand acceptCommand) {
        Dispatched dispatched = new Dispatched(this);
        dispatched.publishAfterCommit();
    }

    public void departure(DepartureCommand departureCommand) {
        Departured departured = new Departured(this);
        departured.publishAfterCommit();
    }

    public static void duplicateCall(Called called) {
        /** Example 1:  new item 
        TaxiCall taxiCall = new TaxiCall();
        repository().save(taxiCall);

        */

        /** Example 2:  finding and process
        
        repository().findById(called.get???()).ifPresent(taxiCall->{
            
            taxiCall // do something
            repository().save(taxiCall);


         });
        */

    }
}
