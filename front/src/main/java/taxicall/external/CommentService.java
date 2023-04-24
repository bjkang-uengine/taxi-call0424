package taxicall.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "taxi", url = "${api.url.taxi}")
public interface CommentService {
    @RequestMapping(
        method = RequestMethod.PUT,
        path = "/comments/{id}/drivercomment"
    )
    public void driverComment(
        @PathVariable("id") Long id,
        @RequestBody DriverCommentCommand driverCommentCommand
    );
}
