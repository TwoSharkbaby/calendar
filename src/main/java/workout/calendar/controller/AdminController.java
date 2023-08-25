package workout.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import workout.calendar.domain.dto.ResourcesDto;
import workout.calendar.domain.dto.userRoleDto;
import workout.calendar.domain.entity.Resources;
import workout.calendar.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class AdminController {

    //    private final ResourcesService resourcesService;
//    private final RoleRepository roleRepository;
//    private final RoleService roleService;
    private final UserService userService;
//    private final UrlFilterInvocationSecurityMetadatsSource urlFilterInvocationSecurityMetadatsSource;

    @GetMapping(value = "/admin")
    public String home() throws Exception {
        return "admin/home";
    }

//    @GetMapping(value = "/admin/resources")
//    public String getResources(Model model) throws Exception {
//
//        List<Resources> resources = resourcesService.getResources();
//        model.addAttribute("resources", resources);
//
//        return "admin/resource/list";
//    }
//
//    @PostMapping(value = "/admin/resources")
//    public String createResources(ResourcesDto resourcesDto) throws Exception {
//
//        ModelMapper modelMapper = new ModelMapper();
//        Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        Resources resources = modelMapper.map(resourcesDto, Resources.class);
//        resources.setRoleSet(roles);
//
//        resourcesService.createResources(resources);
//        urlFilterInvocationSecurityMetadatsSource.reload();
//
//        return "redirect:/admin/resources";
//    }
//
//    @GetMapping(value = "/admin/resources/register")
//    public String viewRoles1(Model model) throws Exception {
//
//        List<Role> roleList = roleService.getRoles();
//        model.addAttribute("roleList", roleList);
//
//        ResourcesDto resources = new ResourcesDto();
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(new Role());
//        resources.setRoleSet(roleSet);
//        model.addAttribute("resources", resources);
//
//        return "admin/resource/detail";
//    }
//
//    @GetMapping(value = "/admin/resources/{id}")
//    public String getResources(@PathVariable String id, Model model) throws Exception {
//
//        List<Role> roleList = roleService.getRoles();
//        model.addAttribute("roleList", roleList);
//        Resources resources = resourcesService.getResources(Long.valueOf(id));
//
//        ModelMapper modelMapper = new ModelMapper();
//        ResourcesDto resourcesDto = modelMapper.map(resources, ResourcesDto.class);
//        model.addAttribute("resources", resourcesDto);
//
//        return "admin/resource/detail";
//    }
//
//    @GetMapping(value = "/admin/resources/delete/{id}")
//    public String removeResources1(@PathVariable String id, Model model) throws Exception {
//
//        Resources resources = resourcesService.getResources(Long.valueOf(id));
//        resourcesService.deleteResources(Long.valueOf(id));
//
//        urlFilterInvocationSecurityMetadatsSource.reload();
//        return "redirect:/admin/resources";
//    }

    @GetMapping(value = "/admin/accounts")
    public String getUsers(@RequestParam(value = "cat", required = false) String cat,
                           @RequestParam(value = "info", required = false) String info,
                           Model model) throws Exception {
        if (info != null) {
//            Page<userRoleDto> users = userService.getUsers();
            model.addAttribute("info", info);
            return "admin/user/list";
        }
        return "admin/user/list";
    }

//    @PostMapping(value="/admin/accounts")
//    public String modifyUser(AccountDto accountDto) throws Exception {
//
//        userService.modifyUser(accountDto);
//
//        return "redirect:/admin/accounts";
//    }
//
//    @GetMapping(value = "/admin/accounts/{id}")
//    public String getUser(@PathVariable(value = "id") Long id, Model model) {
//
//        AccountDto accountDto = userService.getUser(id);
//        List<Role> roleList = roleService.getRoles();
//
//        model.addAttribute("account", accountDto);
//        model.addAttribute("roleList", roleList);
//
//        return "admin/user/detail";
//    }
//
//    @GetMapping(value = "/admin/accounts/delete/{id}")
//    public String removeUser(@PathVariable(value = "id") Long id, Model model) {
//
//        userService.deleteUser(id);
//
//        return "redirect:/admin/users";
//    }
}
