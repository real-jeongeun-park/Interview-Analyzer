package com.interviewanalyzer.controller;

import com.interviewanalyzer.dto.Answer;
import com.interviewanalyzer.dto.Prompt;
import com.interviewanalyzer.service.AiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

// @ResponseBody + Controller
@RestController
public class RestAiController {
    private final AiService aiService;

    public RestAiController(AiService aiService){
        this.aiService = aiService; // dependency injection
    }

    @PostMapping("/home/ai/asked")
    public Answer handlePrompt(@RequestBody Prompt prompt) throws IOException, InterruptedException {
        // 밑에는 디버깅 부분
        // System.out.println(promptDto.getPrompt());
        Answer answer = aiService.startAI(prompt.getPrompt());
        String formatted = answer.getAnswer().replace("\n", "<br/>");
        formatted = formatted.replace("**", "");
        answer.setAnswer(formatted);
        return answer;
    }
}
