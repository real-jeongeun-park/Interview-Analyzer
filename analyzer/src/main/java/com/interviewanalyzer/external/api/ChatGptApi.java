package com.interviewanalyzer.external.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewanalyzer.dto.Answer;
import com.interviewanalyzer.dto.ChatRequest;
import com.interviewanalyzer.dto.ChatResponse;
import com.interviewanalyzer.dto.Message;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ChatGptApi {
    private int maxTokens;
    private String defaultPrompt;

    public ChatGptApi(int maxTokens){
        this.maxTokens = maxTokens;
    }

    public void setDefaultPrompt(String defaultPrompt){
        this.defaultPrompt = defaultPrompt;
    }

    public Answer chatAnswer(String prompt) throws IOException, InterruptedException {
        if(!prompt.isEmpty()){
            // 비어있지 않으면
            ObjectMapper mapper = new ObjectMapper(); // 매핑. 리스트 -> 요청, 응답 -> 리스트 ???
            List<Message> messages = new ArrayList<>();

            if(defaultPrompt != null){
                // 추가로 명령할 내용 있음
                messages.add(new Message("user", defaultPrompt + prompt));
            }
            else{
                messages.add(new Message("user", prompt));
                // prompt 넣음. role은 user 고정
                // 넣고 리스트 차례대로 메시지 읽는 ?
            }

            ChatRequest chatRequest = new ChatRequest("gpt-4o-mini", messages, maxTokens); //  3.5 불러옴
            String requestBody = mapper.writeValueAsString(chatRequest); // message들 전부 string 형태

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                    .header("Content-Type", "application/json") // json 형태로 날림
                    .header("Authorization", 비공개)
                    // github에서 빼기
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build(); // 요청 보낼 곳 지정, 요청 content 타입 지정 -> json

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // response body 값 받아옴

            if(response.statusCode() == 200){
                // OK
                ChatResponse chatResponse = mapper.readValue(response.body(), ChatResponse.class);
                // body 부분 받아서 chatResponse class 형태로 객체 생성
                Answer answer = new Answer();

                answer.setAnswer(chatResponse.choices[0].message.content);
                // System.out.println("ChatGPT: " + answer.getAnswer());// 앞 뒤 공백 제거해 출력함
                // 위는 디버깅 부분
                return answer;
            }

            else return null;
        }
        else return null;
    }
}
