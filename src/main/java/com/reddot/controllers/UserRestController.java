package com.reddot.controllers;

import com.reddot.model.entities.User;
import com.reddot.services.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "/users", tags = "Users")
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
    @ApiOperation(
            value = "Get List of users",
            httpMethod = "GET",
            produces = "application/json",
            response = User.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<List<User>> getUsers() {


        return IUserService.readAllActivatedUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    @ApiOperation(
            value = "Get User by id",
            httpMethod = "GET",
            produces = "application/json",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Don't enter id"),
            @ApiResponse(code = 404, message = "User by this id not found")
    })
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) {
        return IUserService.readById(id);
    }

    @PostMapping("")
    @ApiOperation(
            value = "Create user",
            httpMethod = "POST",
            produces = "application/json",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "User successful created"),
            @ApiResponse(code = 400, message = "Not Valid user data")

    })
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
    @ApiOperation(
            value = "Update user data",
            httpMethod = "PUT",
            produces = "application/json",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Not Valid user data"),
            @ApiResponse(code = 404, message = "User by this id not found")

    })
    public ResponseEntity<User> updateUser(@PathVariable("id") long id,
                                              @RequestBody @Valid User update)  {
       return IUserService.updateUser(id, update);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:admin')")
    @ApiOperation(
            value = "Delete user",
            httpMethod = "DELETE",
            produces = "application/json",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User successful deleted"),
            @ApiResponse(code = 404, message = "User by this id not found")

    })
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        return IUserService.deleteUser(id);
    }

}
