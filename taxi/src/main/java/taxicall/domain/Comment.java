package taxicall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import taxicall.TaxiApplication;

@Entity
@Table(name = "Comment_table")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long taxiid;

    private String commnet;

    @PostPersist
    public void onPostPersist() {}

    public static CommentRepository repository() {
        CommentRepository commentRepository = TaxiApplication.applicationContext.getBean(
            CommentRepository.class
        );
        return commentRepository;
    }

    public void driverComment(DriverCommentCommand driverCommentCommand) {}
}
