package com.reddot.services;

import com.reddot.entities.User;
import com.reddot.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserFacade(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public UserDTO getUserById(Long id){
        return convertToUserDto(userService.findById(id));
    }

    private UserDTO convertToUserDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}
