package taxicall.domain;

import java.util.*;
import lombok.Data;
import taxicall.infra.AbstractEvent;

@Data
public class Called extends AbstractEvent {

    private Long id;
    private Long userid;
    private String userlocation;
    private String destination;
    private String status;
    private String payType;
    private Long taxiId;
    private String driverComment;
}
