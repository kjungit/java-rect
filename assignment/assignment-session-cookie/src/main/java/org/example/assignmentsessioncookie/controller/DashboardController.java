package org.example.assignmentsessioncookie.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Controller
public class DashboardController {

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            Model model,
            HttpServletResponse response,
            @CookieValue(value = "lastVisit", required = false) String lastVisit,
            @CookieValue(value = "theme", defaultValue = "light") String theme
    ) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", username);
        model.addAttribute("theme", theme);

        if (lastVisit != null) {
            long millis = Long.parseLong(lastVisit);
            String readable = Instant.ofEpochMilli(millis)
                    .atZone(ZoneId.systemDefault())
                    .format(FMT);
            model.addAttribute("lastVisit", readable);
        }

        long now = System.currentTimeMillis();
        Cookie cookie = new Cookie("lastVisit", String.valueOf(now));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30); // 30일 유지
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        return "dashboard";
    }

    @GetMapping("/theme")
    public String changeTheme(
            @RequestParam String mode,
            HttpServletResponse response
    ) {
        String value = "dark".equals(mode) ? "dark" : "light";

        Cookie theme = new Cookie("theme", value);
        theme.setPath("/");
        theme.setMaxAge(60 * 60 * 24 * 30);

        response.addCookie(theme);

        return "redirect:/dashboard";
    }

}
