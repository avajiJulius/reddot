package com.reddot.controllers;

import com.reddot.entities.User;
import com.reddot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    public String showUsers(Model model) {
        List<User> userList = userService.findAllActivatedUsers();

        model.addAttribute("userList", userList);
        return "users/home";
    }

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(name = "keyword", required = false) String keyword) {
        Specification<User> specification = Specification.where(usernameContains(keyword))
                .or(firstNameContains(keyword)).or(lastNameContains(keyword));
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
    public String showUserForm(@ModelAttribute("usr") User user) {
        return "users/create";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("usr") User user) {
        user.setStatus("online");
        user.setActivated(true);
        userService.saveUser(user);
        return "redirect:/users/";
    }


    @GetMapping("/{id}/edit")
    public String showUserEditForm(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("usr", userService.findById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@PathVariable(name = "id") Long id,
                           @ModelAttribute User userEdit) {
        userEdit.setStatus("online");
        userEdit.setActivated(true);
        userService.updateUser(id, userEdit);
        return "redirect:/users/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users/";
    }

}
