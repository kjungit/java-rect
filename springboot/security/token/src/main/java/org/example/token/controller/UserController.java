package org.example.token.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/join")
    public String signUp() {
        return "sign-up";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}