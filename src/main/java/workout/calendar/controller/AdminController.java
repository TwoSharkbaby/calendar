package workout.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import workout.calendar.domain.dto.UserRoleDto;
import workout.calendar.service.UserService;

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

    @GetMapping(value = "/admin/accounts")
    public String getUsers(@RequestParam(value = "cat", required = false) String cat,
                           @RequestParam(value = "info", required = false) String info,
                           @PageableDefault(size = 10, sort = "id",
                                   direction = Sort.Direction.DESC) Pageable pageable,
                           Model model) throws Exception {
        if (cat != null) {
            Page<UserRoleDto> users = userService.getUsers(cat, info, pageable);
            int blockLimit = 3;
            int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            int endPage = Math.min((startPage + blockLimit - 1), users.getTotalPages());
            model.addAttribute("users", users);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
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
}
