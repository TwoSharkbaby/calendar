package workout.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home() { return "home";
    }

    @GetMapping("/try/user")
    public String user() {
        return "user";
    }

    @GetMapping("/try/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/try/admin")
    public String admin() {
        return "admin";
    }
}
