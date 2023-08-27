package workout.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workout.calendar.domain.dto.ResourcesDto;
import workout.calendar.domain.dto.UserRoleDto;
import workout.calendar.security.metadatasource.UrlFilterInvocationSecurityMetadatsSource;
import workout.calendar.service.ResourceService;
import workout.calendar.service.UserService;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final ResourceService resourceService;
    private final UserService userService;
    private final UrlFilterInvocationSecurityMetadatsSource urlFilterInvocationSecurityMetadatsSource;

    @GetMapping(value = "/admin")
    public String home() throws Exception {
        return "admin/home";
    }

    @GetMapping(value = "/admin/accounts")
    public String getUsers(@RequestParam(value = "cat", required = false) String cat,
                           @RequestParam(value = "info", required = false) String info,
                           @PageableDefault(size = 10) Pageable pageable,
                           Model model) throws Exception {
        if (cat != null) {
            model.addAttribute("users", userService.getUsers(cat, info, pageable));
        }
        return "admin/user/list";
    }

    @PostMapping(value = "/admin/accounts")
    public String modifyUser(UserRoleDto userRoleDto, Model model, RedirectAttributes rtts) {
        Long id = userService.modifyRole(userRoleDto);
        if (id != null) {
            rtts.addFlashAttribute("result", "true");
            return "redirect:/admin/accounts/" + id;
        } else {
            model.addAttribute("user", userRoleDto);
            return "admin/user/detail";
        }
    }

    @GetMapping(value = "/admin/accounts/{id}")
    public String getUser(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/user/detail";
    }

    @GetMapping(value = "/admin/accounts/delete/{id}")
    public String removeUser(@PathVariable(value = "id") Long id, RedirectAttributes rtts) {
        userService.deleteUser(id);
        rtts.addFlashAttribute("result", "true");
        return "redirect:/admin/accounts";
    }

    @GetMapping(value = "/admin/resources")
    public String getResources(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("resources", resourceService.getResourceWithPage(pageable));
        return "admin/resource/list";
    }

    @PostMapping(value = "/admin/resources")
    public String createResources(ResourcesDto resourcesDto) throws Exception {

//        ModelMapper modelMapper = new ModelMapper();
//        Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        Resources resources = modelMapper.map(resourcesDto, Resources.class);
//        resources.setRoleSet(roles);
//
//        resourcesService.createResources(resources);
//        urlFilterInvocationSecurityMetadatsSource.reload();

        return "redirect:/admin/resources";
    }

    @GetMapping(value = "/admin/resources/register")
    public String viewRoles1(Model model) throws Exception {

//        List<Role> roleList = roleService.getRoles();
//        model.addAttribute("roleList", roleList);
//
//        ResourcesDto resources = new ResourcesDto();
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(new Role());
//        resources.setRoleSet(roleSet);
//        model.addAttribute("resources", resources);

        return "admin/resource/detail";
    }

    @GetMapping(value = "/admin/resources/{id}")
    public String getResources(@PathVariable String id, Model model) throws Exception {

//        List<Role> roleList = roleService.getRoles();
//        model.addAttribute("roleList", roleList);
//        Resources resources = resourcesService.getResources(Long.valueOf(id));
//
//        ModelMapper modelMapper = new ModelMapper();
//        ResourcesDto resourcesDto = modelMapper.map(resources, ResourcesDto.class);
//        model.addAttribute("resources", resourcesDto);

        return "admin/resource/detail";
    }

    @GetMapping(value = "/admin/resources/delete/{id}")
    public String removeResources1(@PathVariable String id, Model model) throws Exception {

//        Resources resources = resourcesService.getResources(Long.valueOf(id));
//        resourcesService.deleteResources(Long.valueOf(id));
//
//        urlFilterInvocationSecurityMetadatsSource.reload();
        return "redirect:/admin/resources";
    }
}
