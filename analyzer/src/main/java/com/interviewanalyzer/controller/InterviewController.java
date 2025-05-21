package com.interviewanalyzer.controller;

import org.springframework.stereotype.Controller;
import com.interviewanalyzer.dto.Character;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/interview")
public class InterviewController {
    @GetMapping("robot")
    public String robot(Model model){
        Character ai = new Character();
        ai.setName("robot");
        ai.setJsName("robotInterviewBubble.js");
        ai.setImgName("robot.png");
        model.addAttribute("selected", ai);

        return "interview";
    }

    @GetMapping("man")
    public String interviewer(Model model){
        // 면접관 넣기
        Character man = new Character();
        man.setName("man");
        man.setJsName("manInterviewBubble.js");
        man.setImgName("man.jpg");
        model.addAttribute("selected", man);

        return "interview";
    }
}
