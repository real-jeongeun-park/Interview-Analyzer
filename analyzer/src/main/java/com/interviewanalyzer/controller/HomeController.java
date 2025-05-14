package com.interviewanalyzer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("home/analyzer")
    public String analyzer(){
        return "analyzer.html";
    }

    @GetMapping("home/interview")
    public String interview(){
        return "interview.html";
    }

    @GetMapping("home/ai")
    public String ai(){
        return "ai.html";
    }

    @GetMapping("home/articles")
    public String articles(){
        return "articles.html";
    }

    @GetMapping("home/community")
    public String community(){
        return "community.html";
    }
}
