package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class 보험HateoasProcessor
    implements RepresentationModelProcessor<EntityModel<보험>> {

    @Override
    public EntityModel<보험> process(EntityModel<보험> model) {
        return model;
    }
}
