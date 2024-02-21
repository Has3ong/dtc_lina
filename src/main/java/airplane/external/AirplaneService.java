package airplane.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "Airplane", url = "${api.url.Airplane}")
public interface AirplaneService {
    @RequestMapping(method = RequestMethod.POST, path = "/airplanes")
    public void reservationReception(@RequestBody Airplane airplane);

    @RequestMapping(method = RequestMethod.POST, path = "/airplanes")
    public void reservationReception(@RequestBody Airplane airplane);

    @RequestMapping(method = RequestMethod.POST, path = "/airplanes")
    public void updateAirplane(@RequestBody Airplane airplane);

    @RequestMapping(method = RequestMethod.POST, path = "/airplanes")
    public void updateAirplane(@RequestBody Airplane airplane);
}
