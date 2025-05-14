package com.interviewanalyzer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/home")
    public String homeTwo(){
        return "home";
    }

    @GetMapping("home/analyzer")
    public String analyzer(){
        return "analyzer";
    }

    @GetMapping("home/interview")
    public String interview(){
        return "interview";
    }

    @GetMapping("home/articles")
    public String articles(){
        return "articles";
    }
}
