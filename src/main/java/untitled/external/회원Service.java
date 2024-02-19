package untitled.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "회원", url = "${api.url.회원}")
public interface 회원Service {
    @RequestMapping(method = RequestMethod.PATCH, path = "/회원")
    public void 청약상태변경(@RequestBody 회원 회원);

    @RequestMapping(method = RequestMethod.PATCH, path = "/회원")
    public void 청약상태변경(@RequestBody 회원 회원);

    @RequestMapping(method = RequestMethod.PATCH, path = "/회원")
    public void 청약상태변경(@RequestBody 회원 회원);

    @RequestMapping(method = RequestMethod.PATCH, path = "/회원")
    public void 청약상태변경(@RequestBody 회원 회원);
}
