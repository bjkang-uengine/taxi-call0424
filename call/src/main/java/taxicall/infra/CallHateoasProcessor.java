package taxicall.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import taxicall.domain.*;

@Component
public class CallHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Call>> {

    @Override
    public EntityModel<Call> process(EntityModel<Call> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/취소")
                .withRel("취소")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/inpitcomment")
                .withRel("inpitcomment")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/inputcomment")
                .withRel("inputcomment")
        );

        return model;
    }
}
