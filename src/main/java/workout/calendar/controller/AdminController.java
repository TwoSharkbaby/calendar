package workout.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workout.calendar.domain.dto.resources.ResourcesDto;
import workout.calendar.domain.dto.user.UserRoleFormDto;
import workout.calendar.service.ResourceService;
import workout.calendar.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final ResourceService resourceService;
    private final UserService userService;

    @GetMapping
    public String adminHome() {
        return "admin/home";
    }

    @GetMapping(value = "/accounts")
    public String usersList(@RequestParam(value = "cat", required = false) String cat,
                           @RequestParam(value = "info", required = false) String info,
                           @PageableDefault(size = 10) Pageable pageable,
                           Model model) {
        if (cat != null) {
            model.addAttribute("users", userService.getUsers(cat, info, pageable));
        }
        return "admin/user/list";
    }

    @GetMapping(value = "/accounts/{id}")
    public String userDetail(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserRoleForm(id));
        return "admin/user/detail";
    }

    @PostMapping(value = "/accounts")
    public String modifyUser(UserRoleFormDto userRoleFormDto, Model model, RedirectAttributes rtts) {
        Long id = userService.modifyRole(userRoleFormDto);
        if (id != null) {
            rtts.addFlashAttribute("result", "modifyTrue");
            return "redirect:/admin/accounts/" + id;
        } else {
            model.addAttribute("user", userRoleFormDto);
            return "admin/user/detail";
        }
    }

    @GetMapping(value = "/accounts/delete/{id}")
    public String removeUser(@PathVariable(value = "id") Long id, RedirectAttributes rtts) {
        userService.deleteUser(id);
        rtts.addFlashAttribute("result", "deleteTrue");
        return "redirect:/admin/accounts";
    }

    @GetMapping(value = "/resources")
    public String resourcesList(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("resources", resourceService.getResourcesWithPage(pageable));
        return "admin/resource/list";
    }

    @GetMapping(value = "/resources/{id}")
    public String resourcesDetail(@PathVariable Long id, Model model) {
        model.addAttribute("resources", resourceService.getResource(id));
        return "admin/resource/detail";
    }

    @GetMapping(value = "/resources/register")
    public String resourcesResisterForm(Model model) {
        model.addAttribute("resources", new ResourcesDto());
        return "admin/resource/register";
    }

    @PostMapping(value = "/resources/register")
    public String resisterResources(@Valid ResourcesDto resourcesDto, BindingResult result, RedirectAttributes rtts, Model model) {
        if (result.hasErrors()){
            return "admin/resource/register";
        }
        Long id = resourceService.createResource(resourcesDto);
        if (id == null){
            model.addAttribute("resources", resourcesDto);
            model.addAttribute("result", "createFalse");
            return "admin/resource/register";
        }
        rtts.addFlashAttribute("result", "createTrue");
        return "redirect:/admin/resources/" + id;
    }

    @PostMapping(value = "/resources")
    public String modifyResources(@Valid ResourcesDto resourcesDto, BindingResult result, RedirectAttributes rtts, Model model) {
        if (result.hasErrors()){
            return "admin/resource/detail";
        }
        Long id = resourceService.modifyResource(resourcesDto);
        if (id == null){
            model.addAttribute("resources", resourcesDto);
            model.addAttribute("result", "modifyFalse");
            return "admin/resource/detail";
        }
        rtts.addFlashAttribute("result", "modifyTrue");
        return "redirect:/admin/resources/" + id ;
    }

    @GetMapping(value = "/resources/delete/{id}")
    public String removeResources(@PathVariable Long id, RedirectAttributes rtts) {
        resourceService.deleteResource(id);
        rtts.addFlashAttribute("result", "deleteTrue");
        return "redirect:/admin/resources";
    }
}
