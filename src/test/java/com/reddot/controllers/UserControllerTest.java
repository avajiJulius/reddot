package com.reddot.controllers;

import com.reddot.model.entities.User;
import com.reddot.services.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    private UserService IUserService;
    @Mock
    private Model model;

    private List<User> userList;
    private MockMvc mockMvc;



}