package airplane.infra;

import airplane.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
@RequestMapping(value="/admins")
@Transactional
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/hello")
    public String hello() {
        return "hello spring boot!";
    }
}
//>>> Clean Arch / Inbound Adaptor
