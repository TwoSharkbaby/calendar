package workout.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import workout.calendar.domain.auth.PrincipalDetails;
import workout.calendar.service.RecodeService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RecodeService recodeService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("recodeMonthListDto", recodeService.getMonthRecode());
        model.addAttribute("recodeYearListDto", recodeService.getYearRecode());
        return "home";
    }

}
