package taxicall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import taxicall.PointApplication;
import taxicall.domain.Accumulationed;

@Entity
@Table(name = "Point_table")
@Data
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String status;

    private String point;

    @PostPersist
    public void onPostPersist() {
        Accumulationed accumulationed = new Accumulationed(this);
        accumulationed.publishAfterCommit();
    }

    public static PointRepository repository() {
        PointRepository pointRepository = PointApplication.applicationContext.getBean(
            PointRepository.class
        );
        return pointRepository;
    }

    public static void pointAccumulation(Paymented paymented) {
        /** Example 1:  new item 
        Point point = new Point();
        repository().save(point);

        Accumulationed accumulationed = new Accumulationed(point);
        accumulationed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymented.get???()).ifPresent(point->{
            
            point // do something
            repository().save(point);

            Accumulationed accumulationed = new Accumulationed(point);
            accumulationed.publishAfterCommit();

         });
        */

    }

    public static void pointDeduction(Canceled canceled) {
        /** Example 1:  new item 
        Point point = new Point();
        repository().save(point);

        */

        /** Example 2:  finding and process
        
        repository().findById(canceled.get???()).ifPresent(point->{
            
            point // do something
            repository().save(point);


         });
        */

    }
}
