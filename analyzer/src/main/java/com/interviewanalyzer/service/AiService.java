package com.interviewanalyzer.service;

import com.interviewanalyzer.dto.Answer;
import com.interviewanalyzer.external.api.ChatGptApi;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class AiService {
    public Answer startAI(String prompt) throws IOException, InterruptedException {
        ChatGptApi chatApi = new ChatGptApi(500);
        return chatApi.chatAnswer(prompt);
    }
}
