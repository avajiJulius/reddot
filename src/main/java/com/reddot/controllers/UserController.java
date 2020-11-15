package com.reddot.controllers;

import com.reddot.entities.User;
import com.reddot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.reddot.repositories.specifications.UserSpecification.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Integer INITIAL_PAGE = 0;
    private static final Integer PAGE_SIZE = 10;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    @PreAuthorize("hasAuthority('user:write')")
    public String showUsers(Model model) {
        List<User> userList = userService.findAllActivatedUsers();

        model.addAttribute("userList", userList);
        return "users/home";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String showUser(Model model,
                           @PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/user";
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('user:read')")
    public String search(Model model,
                         @RequestParam(name = "keyword", required = false) String keyword) {
        Specification<User> specification = Specification.where(usernameContains(keyword)).and(activatedContains(true));
        List<User> userList = userService.search(specification);

        model.addAttribute("userList", userList);

        return "users/home";
    }

//    @GetMapping("/page/{page}")
//    public String searchForUsers(Model model,
//                           @PathVariable(name = "page") Optional<Integer> page,
//                           @Param("keyword") String keyword) {
//
//        final int currentPage = (page.orElse(0) < 1 ) ? INITIAL_PAGE : page.get() - 1;
//
//
//
//        List<User> result = users.getContent();
//        model.addAttribute("userList", result);
//        model.addAttribute("page", currentPage);
//        model.addAttribute("totalPages", users.getTotalPages());
//
//        return "users/users";
//    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('user:write')")
    public String showUserForm(@ModelAttribute("usr") User user) {
        return "users/create";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('user:write')")
    public String createUser(@ModelAttribute("usr") User user) {
        userService.saveUser(user);
        return "redirect:/users/";
    }


    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('user:write')")
    public String showUserEditForm(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("usr", userService.findById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String editUser(@PathVariable(name = "id") Long id,
                           @ModelAttribute User userEdit) {
        userService.updateUser(id, userEdit);
        return "redirect:/users/";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users/";
    }

}
