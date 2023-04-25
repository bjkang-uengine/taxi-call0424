package taxicall.domain;

import java.util.*;
import lombok.*;
import taxicall.domain.*;
import taxicall.infra.AbstractEvent;

@Data
@ToString
public class Commented extends AbstractEvent {

    private Long id;
    private Long userid;
    private String userlocation;
    private String destination;
    private String status;
    private String payType;
    private Long taxiId;
    private String driverComment;

    public Commented(Call aggregate) {
        super(aggregate);
    }

    public Commented() {
        super();
    }
}
