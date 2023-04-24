package taxicall.external;

import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    /**
     * Fallback
     */
    public Comment getComment(Long id) {
        Comment comment = new Comment();
        return comment;
    }
}
