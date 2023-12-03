package com.service.bearrecipes.controller.rest;

import com.service.bearrecipes.model.User;
import com.service.bearrecipes.service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user/registration")
    public void registration(@RequestBody @NotNull User user) {
        userService.saveUser(user);
    }
}
