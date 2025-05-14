package com.interviewanalyzer.dto;

import java.util.List;

public class ChatRequest {
    public String model;
    public List<Message> messages; // 메세지 여러 개
    public int max_tokens;

    public ChatRequest(String model, List<Message> messages, int max_tokens){
        // model은 현재 gpt-3.5-turbo가 제일 저렴하니 3.5로
        this.model = model;
        this.messages = messages;
        this.max_tokens = max_tokens; // 주입
    }
}
