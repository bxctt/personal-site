package com.xiaochen.sites.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/homes")
    public String home(Model model) {
        model.addAttribute("message", "Hello Page!");

        return "home";
    }
}
