package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class 심사HateoasProcessor
    implements RepresentationModelProcessor<EntityModel<심사>> {

    @Override
    public EntityModel<심사> process(EntityModel<심사> model) {
        return model;
    }
}
