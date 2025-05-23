package com.interviewanalyzer.controller;

import com.interviewanalyzer.dto.Answer;
import com.interviewanalyzer.dto.Transcript;
import com.interviewanalyzer.service.SpeechService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class SpeechController {
    private final SpeechService speechService;

    @Autowired
    public SpeechController(SpeechService speechService){
        this.speechService = speechService;
    }

    @GetMapping("/home/interview/result")
    public String result(HttpSession session, Model model){
        Answer answer = (Answer)session.getAttribute("answer");
        String formattedAnswer = answer.getAnswer().replace("\n", "<br/>");
        formattedAnswer = formattedAnswer.replace("**", "");
        model.addAttribute("answer", formattedAnswer);
        return "interviewResult";
    }

    @PostMapping("/home/interview/result")
    @ResponseBody
    public String result(@RequestBody Transcript res, HttpSession session) throws IOException, InterruptedException {
        Answer answer = speechService.startChat(res.getTranscript());
        System.out.println(answer.getAnswer());
        session.setAttribute("answer", answer);
        return "ok";
    }
}
