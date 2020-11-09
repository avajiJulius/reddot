package com.reddot.controllers;

import com.reddot.entities.User;
import com.reddot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String getUsers(Model model) {
        List<User> userList = userService.findAllUsers();
        model.addAttribute("userList", userList);
        return "users/users";
    }

    @GetMapping("/new")
    public String showUserForm(@ModelAttribute("usr") User user) {
        return "users/create";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("usr") User user) {
        user.setStatus("online");
        user.setActivated(true);
        userService.saveUser(user);
        return "redirect:/users";
    }


    @GetMapping("/{id}")
    public String getUserForEdit(@PathVariable(name = "id")Long id,
                                  Model model) {
        List<User> userList = null;
        User user = userService.findById(id);
        userList.add(user);
        model.addAttribute("userList", userList);
        return "users";
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
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
