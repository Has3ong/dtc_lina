package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class ReviewHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Review>> {

    @Override
    public EntityModel<Review> process(EntityModel<Review> model) {
        return model;
    }
}
