package nl.novi.beachmatch.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class UserController {

    @GetMapping("/users/{username}")
    public String getUser(@PathVariable("username") String username) {
        return "You are the owner";
    }
}
