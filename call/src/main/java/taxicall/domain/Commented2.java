package taxicall.domain;

import java.util.*;
import lombok.*;
import taxicall.domain.*;
import taxicall.infra.AbstractEvent;

@Data
@ToString
public class Commented2 extends AbstractEvent {

    private Long id;
    private Long userid;
    private String userlocation;
    private String destination;
    private String status;
    private String payType;
    private Long taxiId;
    private String driverComment;

    public Commented2(Call aggregate) {
        super(aggregate);
    }

    public Commented2() {
        super();
    }
}
