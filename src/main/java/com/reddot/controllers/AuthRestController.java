package com.reddot.controllers;

import com.reddot.model.dto.AuthenticationRequestDTO;
import com.reddot.model.entities.User;
import com.reddot.security.jwt.JwtTokenProvider;
import com.reddot.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Api(value = "/auth", tags = "Authentication Controller")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private UserService UserService;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManager, UserService UserService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.UserService = UserService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Login using username and password",
            httpMethod = "POST",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Invalid email or password")
    })
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            String username = request.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,request.getPassword()));
            User user = UserService.findByUsername(username);
            String token = jwtTokenProvider.createToken(username, user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
