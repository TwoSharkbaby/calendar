package workout.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workout.calendar.domain.dto.UserModifyFormDto;
import workout.calendar.domain.dto.UserResisterFormDto;
import workout.calendar.domain.entity.User;
import workout.calendar.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) throws Exception {
        model.addAttribute("userResisterFormDto", new UserResisterFormDto());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserResisterFormDto userResisterFormDto, BindingResult result, RedirectAttributes rtts, Model model) {
        if (result.hasErrors()) {
            return "user/register";
        } else {
            Long id = userService.createUser(userResisterFormDto);
            if (id == null) {
                model.addAttribute("userResisterFormDto", userResisterFormDto);
                model.addAttribute("result", "registerFalse");
                return "user/register";
            } else {
                rtts.addFlashAttribute("result", "registerTrue");
                return "redirect:/user/loginForm";
            }
        }
    }

    // 접속자랑 아이디 같은지 확인
    @GetMapping("/modify/{id}")
    public String modifyForm(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("userModifyFormDto", userService.getUserModifyForm(id));
        return "user/modify";
    }

    // 접속자랑 아이디 같은지 확인
    @PostMapping("/modify")
    public String modify(@Valid UserModifyFormDto userModifyFormDto, BindingResult result,
                         RedirectAttributes rtts, Model model, HttpServletRequest request) {
        System.out.println("userModifyFormDto = " + userModifyFormDto);
        if (result.hasErrors()) {
            return "user/modify";
        } else {
            Long id = userService.modifyUser(userModifyFormDto);
            if (id == null) {
                model.addAttribute("userResisterFormDto", userModifyFormDto);
                model.addAttribute("result", "modifyFalse");
                return "user/modify";
            } else {
                rtts.addFlashAttribute("result", "modifyTrue");
                new SecurityContextLogoutHandler().logout(request, null, null);
                return "redirect:/user/loginForm";
            }
        }
    }

    @GetMapping("/loginForm")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            RedirectAttributes rtts) {
        rtts.addFlashAttribute("error", error);
        return "user/loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes rtts) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        rtts.addFlashAttribute("result", "logoutTrue");
        return "redirect:/";
    }





}
