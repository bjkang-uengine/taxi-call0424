package taxicall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Mypage_table")
@Data
public class Mypage {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String userLocation;
    private String destination;
    private String status;
    private Long callid;
    private String taxiNumber;
    private String taxiModel;
    private String driverName;
    private String driverMobileNumber;
}
