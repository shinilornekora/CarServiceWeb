package org.shiniasse.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("")
    public String mainPage() {
        return "home";
    }

    @GetMapping("/top")
    public String topPage() {
        return "page-top";
    }
}
