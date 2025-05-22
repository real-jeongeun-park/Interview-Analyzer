package com.interviewanalyzer.controller;
import com.interviewanalyzer.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class RestSpeechController {
    private final SpeechService speechService;

    @Autowired
    public RestSpeechController(SpeechService speechService) {
        this.speechService = speechService;
    }

    @PostMapping("/home/interview/transcribe")
    public Map<String, String> transcribe(@RequestParam("file") MultipartFile file) throws Exception {
        Map<String, String> res = speechService.transcribe(file);
        return res;
    }
}