package com.interviewanalyzer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AiController {
    @GetMapping("/home/ai")
    public String Ai(){
        return "ai";
    }
}
