package com.interviewanalyzer.service;

import com.interviewanalyzer.dto.Answer;
import com.interviewanalyzer.dto.Result;
import com.interviewanalyzer.external.api.ChatGptApi;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AnalysisService {
    public Result analyze(String content) throws IOException, InterruptedException {
        String strengths, weaknesses, recap;
        ChatGptApi chatApi = new ChatGptApi(250); // tokens 수 넣어줌

        // 장점 받아옴
        chatApi.setDefaultPrompt("내가 쓴 다음의 자기소개서의 장점에 대해 짧게 존댓말로, 하지만 친근하게 설명해 줘. 안녕하세요와 같은 문구는 생략해도 좋아.\n\n");
        strengths = chatApi.chatAnswer(content).getAnswer();

        // 단점 받아옴
        chatApi.setDefaultPrompt("내가 쓴 다음의 자기소개서의 보안할 점에 대해 짧게 존댓말로, 하지만 친근하게 설명해 줘. 안녕하세요와 같은 문구는 생략해도 좋아.\n\n");
        weaknesses = chatApi.chatAnswer(content).getAnswer();

        // 요약 받아옴
        chatApi.setDefaultPrompt("내가 쓴 다음의 자기소개서에 대한 평가를 요약해 짧게 존댓말로, 하지만 친근하게 설명해 줘. 안녕하세요와 같은 문구는 생략해도 좋아.\n\n");
        recap = chatApi.chatAnswer(content).getAnswer();

        return new Result(strengths, weaknesses, recap);
    }
}
