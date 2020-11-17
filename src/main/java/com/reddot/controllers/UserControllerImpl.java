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

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController{

    private static final Integer INITIAL_PAGE = 0;
    private static final Integer PAGE_SIZE = 10;

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public List<User> showUsers() {
        return userService.findAllActivatedUsers();
    }

    @GetMapping("/{id}")
    public User showUser(@PathVariable(name = "id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam(name = "keyword", required = false) String keyword) {
        Specification<User> specification = Specification.where(usernameContains(keyword)).and(activatedContains(true));
        List<User> userList = userService.search(specification);


        return userList;
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

    @Override
    public String createUser(@ModelAttribute("usr") User user) {
        userService.saveUser(user);
        return "User successful created";
    }

    @Override
    public String editUser(@PathVariable(name = "id") Long id,
                           @ModelAttribute User userEdit) {
        userService.updateUser(id, userEdit);
        return "Success update";
    }

    @Override
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "Success delete";
    }

}
