package untitled.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "Member", url = "${api.url.Member}")
public interface MemberService {
    @RequestMapping(method = RequestMethod.PATCH, path = "/members")
    public void changeSubscriptionStatus(@RequestBody Member member);

    @RequestMapping(method = RequestMethod.PATCH, path = "/members")
    public void changeSubscriptionStatus(@RequestBody Member member);

    @RequestMapping(method = RequestMethod.PATCH, path = "/members")
    public void changeSubscriptionStatus(@RequestBody Member member);

    @RequestMapping(method = RequestMethod.PATCH, path = "/members")
    public void changeSubscriptionStatus(@RequestBody Member member);
}
