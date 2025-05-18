package com.interviewanalyzer.deprecated;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpeechService {

    public List<Map<String, Object>> transcribe(MultipartFile file) {
        List<Map<String, Object>> results = new ArrayList<>();
        try (SpeechClient speechClient = SpeechClient.create()) {
            ByteString audioBytes = ByteString.readFrom(file.getInputStream());

            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.WEBM_OPUS)
                    .setLanguageCode("ko-KR")
                    .setSampleRateHertz(48000)
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);

            for (SpeechRecognitionResult result : response.getResultsList()) {
                for (SpeechRecognitionAlternative alt : result.getAlternativesList()) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("transcript", alt.getTranscript());
                    item.put("confidence", alt.getConfidence());
                    results.add(item);
                }
            }
        } catch (Exception e) {
            //  로그 확인 포인트
            e.printStackTrace();  // 또는 logger.error("Speech error", e);
            throw new RuntimeException("Speech transcription failed", e);
        }
        return results;
    }
}