package taxicall.domain;

import java.util.*;
import lombok.*;
import taxicall.domain.*;
import taxicall.infra.AbstractEvent;

@Data
@ToString
public class Boardinged extends AbstractEvent {

    private Long id;
    private String number;
    private String model;
    private String driverName;
    private String driverMobileNumber;
    private Long callId;
    private String status;
    private String userId;
    private Long pay;

    public Boardinged(TaxiCall aggregate) {
        super(aggregate);
    }

    public Boardinged() {
        super();
    }
}
