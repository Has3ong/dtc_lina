package airplane.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "Reservation", url = "${api.url.Reservation}")
public interface ReservationService {
    @RequestMapping(method = RequestMethod.PATCH, path = "/reservations")
    public void reservationUpdate(@RequestBody Reservation reservation);

    @RequestMapping(method = RequestMethod.PATCH, path = "/reservations")
    public void reservationUpdate(@RequestBody Reservation reservation);
}
