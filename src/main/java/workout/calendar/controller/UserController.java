package workout.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import workout.calendar.domain.dto.FormLoginUserDto;
import workout.calendar.domain.entity.User;
import workout.calendar.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/try/user")
    public String user(){
        return "user";
    }

    @GetMapping("/try/manager")
    public String manager(){
        return "manager";
    }

    @GetMapping("/try/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("formLoginUserDto", new FormLoginUserDto());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid FormLoginUserDto formLoginUserDto, BindingResult result){
        if (result.hasErrors()){
            return "user/register";
        }
        userService.register(formLoginUserDto);
        return "redirect:user/loginForm";
    }

}
