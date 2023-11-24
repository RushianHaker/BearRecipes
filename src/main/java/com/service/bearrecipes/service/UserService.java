package com.service.bearrecipes.service;

import com.service.bearrecipes.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void saveUser(@NotNull User user);
}
