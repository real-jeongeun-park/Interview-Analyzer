package com.interviewanalyzer.deprecated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.List;

public class SpeechController {
    private final SpeechService speechService;

    @Autowired
    public SpeechController(SpeechService speechService){
        this.speechService = speechService;
    }

    @PostMapping("/home/interview/transcribe")
    public List<Map<String, Object>> transcribe(@RequestParam("file") MultipartFile file) throws Exception {
        return speechService.transcribe(file);
    }
}
