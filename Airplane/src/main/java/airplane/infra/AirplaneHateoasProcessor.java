package airplane.infra;

import airplane.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AirplaneHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Airplane>> {

    @Override
    public EntityModel<Airplane> process(EntityModel<Airplane> model) {
        return model;
    }
}
