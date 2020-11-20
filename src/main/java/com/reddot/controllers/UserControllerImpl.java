package com.reddot.controllers;

import com.reddot.dto.UserDTO;
import com.reddot.services.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControllerImpl {

    private static final Integer INITIAL_PAGE = 0;
    private static final Integer PAGE_SIZE = 10;

    private final UserFacade userFacade;

    @Autowired
    public UserControllerImpl(UserFacade userFacade) {
        this.userFacade = userFacade;
    }


//
//    @Override
//    public List<User> showUsers() {
//        return userService.findAllActivatedUsers();
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") Long id) {
        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        UserDTO user = userFacade.getUserById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public List<User> search(@RequestParam(name = "keyword", required = false) String keyword) {
//        Specification<User> specification = Specification.where(usernameContains(keyword)).and(activatedContains(true));
//        List<User> userList = userService.search(specification);
//
//
//        return userList;
//    }
//
////    @GetMapping("/page/{page}")
////    public String searchForUsers(Model model,
////                           @PathVariable(name = "page") Optional<Integer> page,
////                           @Param("keyword") String keyword) {
////
////        final int currentPage = (page.orElse(0) < 1 ) ? INITIAL_PAGE : page.get() - 1;
////
////
////
////        List<User> result = users.getContent();
////        model.addAttribute("userList", result);
////        model.addAttribute("page", currentPage);
////        model.addAttribute("totalPages", users.getTotalPages());
////
////        return "users/users";
////    }
//
//    @Override
//    public String createUser(@ModelAttribute("usr") User user) {
//        userService.saveUser(user);
//        return "User successful created";
//    }
//
//    @Override
//    public String editUser(@PathVariable(name = "id") Long id,
//                           @ModelAttribute User userEdit) {
//        userService.updateUser(id, userEdit);
//        return "Success update";
//    }
//
//    @Override
//    public String deleteUser(@PathVariable(name = "id") Long id) {
//        userService.deleteUser(id);
//        return "Success delete";
//    }
//
}
