package com.service.bearrecipes.controller.page;

import com.service.bearrecipes.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPageController {

    private final UserService userService;

    public UserPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/user/profileuser"})
    public String profileUserPage(Model model) {
        model.addAttribute("user", userService.findUserByContext());
        return "profileuser";
    }

    @GetMapping({"/user/registration"})
    public String registrationPage() {
        return "userregistration";
    }
}
