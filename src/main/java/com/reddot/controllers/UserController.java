package com.reddot.controllers;

import com.reddot.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UserController {

    @GetMapping("/")
    @ResponseBody
    List<User> showUsers();

    User showUser(@PathVariable(name = "id") Long id);

    @GetMapping("/search")
     List<User> search(@RequestParam(name = "keyword", required = false) String keyword);

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

    @PostMapping()
    String createUser(@ModelAttribute("usr") User user);



    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('user:user')")
    String editUser(@PathVariable(name = "id") Long id,
                           @ModelAttribute User userEdit);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:admin')")
    String deleteUser(@PathVariable(name = "id") Long id);

}
