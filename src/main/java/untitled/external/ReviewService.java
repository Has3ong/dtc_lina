package untitled.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "Review", url = "${api.url.Review}")
public interface ReviewService {
    @RequestMapping(method = RequestMethod.POST, path = "/reviews")
    public void insuranceReview(@RequestBody Review review);

    @RequestMapping(method = RequestMethod.POST, path = "/reviews")
    public void insuranceReview(@RequestBody Review review);

    @RequestMapping(method = RequestMethod.PATCH, path = "/reviews")
    public void subscriptionStatusChangeByReviewer(@RequestBody Review review);

    @RequestMapping(method = RequestMethod.PATCH, path = "/reviews")
    public void subscriptionStatusChangeByReviewer(@RequestBody Review review);
}
