package taxicall.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taxicall.domain.*;

@RestController
// @RequestMapping(value="/comments")
@Transactional
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(
        value = "comments/",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Comment driverComment(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody Comment comment
    ) throws Exception {
        System.out.println("##### /comment/driverComment  called #####");
        comment.driverComment(driverCommentcommand);
        commentRepository.save(comment);
        return comment;
    }
}
