package untitled.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "심사", url = "${api.url.심사}")
public interface 심사Service {
    @RequestMapping(method = RequestMethod.POST, path = "/심사")
    public void 보험료결제완료(@RequestBody 심사 심사);

    @RequestMapping(method = RequestMethod.POST, path = "/심사")
    public void 보험료결제완료(@RequestBody 심사 심사);

    @RequestMapping(method = RequestMethod.PATCH, path = "/심사")
    public void 청약상태변경(@RequestBody 심사 심사);

    @RequestMapping(method = RequestMethod.PATCH, path = "/심사")
    public void 청약상태변경(@RequestBody 심사 심사);
}
