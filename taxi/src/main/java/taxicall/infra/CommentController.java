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
        value = "comments/{id}/drivercomment",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Comment driverComment(
        @PathVariable(value = "id") Long id,
        @RequestBody DriverCommentCommand driverCommentCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /comment/driverComment  called #####");
        Optional<Comment> optionalComment = commentRepository.findById(id);

        optionalComment.orElseThrow(() -> new Exception("No Entity Found"));
        Comment comment = optionalComment.get();
        comment.driverComment(driverCommentCommand);

        commentRepository.save(comment);
        return comment;
    }
}
