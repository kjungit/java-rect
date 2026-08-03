package org.example.basicboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2RedirectController {

    @GetMapping("/oauth2/redirect")
    public String oauth2Redirect() {
        return "oauth2-redirect";
    }
}