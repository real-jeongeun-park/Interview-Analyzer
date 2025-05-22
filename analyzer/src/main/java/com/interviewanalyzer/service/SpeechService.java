package com.interviewanalyzer.service;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import com.interviewanalyzer.dto.Answer;
import com.interviewanalyzer.external.api.ChatGptApi;
import org.springframework.stereotype.Service;;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class SpeechService {
    public Map<String, String> transcribe(MultipartFile file) throws IOException {
        byte[] audioBytes = file.getBytes();

        try(SpeechClient speechClient = SpeechClient.create()){
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.WEBM_OPUS)
                    .setSampleRateHertz(48000)
                    .setLanguageCode("ko-KR")
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(audioBytes))
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);

            StringBuilder transcript = new StringBuilder();
            for(SpeechRecognitionResult result : response.getResultsList()){
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                transcript.append(alternative.getTranscript()).append('\n');
            }

            System.out.println("Success");
            return Map.of("transcript", transcript.toString().trim());
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Answer startChat(String transcript) throws IOException, InterruptedException {
        ChatGptApi chatApi = new ChatGptApi(1000);
        chatApi.setDefaultPrompt("다음은 사용자가 웹사이트에서 진행한 모의 면접에 대한 필사본이야. 횡설수설하거나, 어색한 면접 답변 부분을 찾아서 올바르게 지적해줘 : \n");
        return chatApi.chatAnswer(transcript);
    }
}