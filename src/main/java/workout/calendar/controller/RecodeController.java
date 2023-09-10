package workout.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import workout.calendar.service.RecodeService;

@Controller
@RequestMapping("/recode")
@RequiredArgsConstructor
public class RecodeController {

    private final RecodeService recodeService;

    @GetMapping
    public String main(Model model) {
        model.addAttribute("recode", recodeService.getRecodeAll());
        model.addAttribute("recodeMonthListDto", recodeService.getMonthRecode());
        model.addAttribute("recodeYearListDto", recodeService.getYearRecode());
        return "recode/home";
    }

}
