package org.shiniasse.controllers;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @GetMapping("")
    public String mainPage() {
        LOG.log(Level.INFO, "Going to the main page");
        return "home";
    }

    @GetMapping("/top")
    public String topPage() {
        LOG.log(Level.INFO, "Going to the top page");

        return "page-top";
    }
}
