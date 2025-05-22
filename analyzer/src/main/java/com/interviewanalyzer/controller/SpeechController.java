package com.interviewanalyzer.controller;

import com.interviewanalyzer.dto.Answer;
import com.interviewanalyzer.dto.Transcript;
import com.interviewanalyzer.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String resultTwo(){
        return "interviewResult";
    }

    @PostMapping("/home/interview/result")
    public String result(@RequestBody Transcript res, RedirectAttributes redirectAttributes) throws IOException, InterruptedException {
        Answer answer = speechService.startChat(res.getTranscript());
        redirectAttributes.addFlashAttribute("result", answer.getAnswer());
        return "redirect:/home/interview/result";
    }
}
