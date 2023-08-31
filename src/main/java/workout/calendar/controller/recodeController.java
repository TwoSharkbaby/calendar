package workout.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recode")
@RequiredArgsConstructor
public class recodeController {

    @GetMapping
    public String main(Model model) throws Exception {
//        model.addAttribute("userResisterFormDto", new UserResisterFormDto());
        return "recode/home";
    }
}
