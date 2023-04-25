package taxicall.external;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
public class DriverCommentCommand {

    private Long taxiId;
    private String driverComment;
}
