package com.reddot.rest;

import com.reddot.entities.User;
import com.reddot.services.UserService;
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
@RequestMapping("/users")
public class UserRestController {
    private static final Integer INITIAL_PAGE = 0;
    private static final Integer PAGE_SIZE = 10;

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<List<User>> getUsers() {


        List<User> users = userService.findAllActivatedUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) {
        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User user = userService.findById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        HttpHeaders headers = new HttpHeaders();

        if(user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        userService.saveUser(user);
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
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

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user:user')")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id,
                                              @RequestBody @Valid User user)  {
        HttpHeaders headers = new HttpHeaders();

        if(user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        userService.updateUser(id, user);
        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user:admin')")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
