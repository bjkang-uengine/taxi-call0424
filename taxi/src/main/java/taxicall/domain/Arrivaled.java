package taxicall.domain;

import java.util.*;
import lombok.*;
import taxicall.domain.*;
import taxicall.infra.AbstractEvent;

@Data
@ToString
public class Arrivaled extends AbstractEvent {

    private Long id;
    private String number;
    private String model;
    private String driverName;
    private String driverMobileNumber;
    private Long callId;
    private String status;
    private String userId;
    private Long pay;

    public Arrivaled(TaxiCall aggregate) {
        super(aggregate);
    }

    public Arrivaled() {
        super();
    }
}
