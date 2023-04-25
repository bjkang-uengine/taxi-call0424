package taxicall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import taxicall.CallApplication;
import taxicall.domain.Called;

@Entity
@Table(name = "Call_table")
@Data
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userid;

    private String userlocation;

    private String destination;

    private String status;

    private String payType;

    private Long taxiId;

    private String driverComment;

    @PostPersist
    public void onPostPersist() {
        Called called = new Called(this);
        called.publishAfterCommit();
    }

    public static CallRepository repository() {
        CallRepository callRepository = CallApplication.applicationContext.getBean(
            CallRepository.class
        );
        return callRepository;
    }

    public void cancel() {
        Canceled canceled = new Canceled(this);
        canceled.publishAfterCommit();
    }

    public void inputComment(InputCommentCommand inputCommentCommand) {
        Commented commented = new Commented(this);
        commented.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        taxicall.external.Comment comment = new taxicall.external.Comment();
        // mappings goes here
        CallApplication.applicationContext
            .getBean(taxicall.external.CommentService.class)
            .driverComment(comment);
    }

    public void inputComment2(InputComment2Command inputComment2Command) {
        Commented2 commented2 = new Commented2(this);
        commented2.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        taxicall.external.Comment comment = new taxicall.external.Comment();
        // mappings goes here
        CallApplication.applicationContext
            .getBean(taxicall.external.CommentService.class)
            .driverComment2(comment);
    }

    public static void updateTaxiInfo(Dispatched dispatched) {
        /** Example 1:  new item 
        Call call = new Call();
        repository().save(call);

        */

        /** Example 2:  finding and process
        
        repository().findById(dispatched.get???()).ifPresent(call->{
            
            call // do something
            repository().save(call);


         });
        */

    }

    public static void updateStatus(Departured departured) {
        /** Example 1:  new item 
        Call call = new Call();
        repository().save(call);

        */

        /** Example 2:  finding and process
        
        repository().findById(departured.get???()).ifPresent(call->{
            
            call // do something
            repository().save(call);


         });
        */

    }
}
