package taxicall.external;

import java.util.Date;
import lombok.Data;

@Data
public class Comment {

    private Long id;
    private Long taxiid;
    private String commnet;
}
