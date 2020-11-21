package com.reddot.controllers;

import com.reddot.model.entities.User;
import com.reddot.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    private static final Integer INITIAL_PAGE = 0;
    private static final Integer PAGE_SIZE = 10;

    private final IUserService IUserService;

    @Autowired
    public UserRestController(IUserService IUserService) {
        this.IUserService = IUserService;
    }


    @GetMapping("")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<List<User>> getUsers() {


        return IUserService.readAllActivatedUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) {
        return IUserService.readById(id);
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        return IUserService.createUser(user);
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

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:user')")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id,
                                              @RequestBody @Valid User update)  {
       return IUserService.updateUser(id, update);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:admin')")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        return IUserService.deleteUser(id);
    }

}
