package com.interviewanalyzer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewanalyzer.dto.Article;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller
public class ArticlesController {
    @GetMapping("/home/articles")
    public String articles(Model model) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/json/articles.json");
        InputStream inputStream = resource.getInputStream();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Article> articles = objectMapper.readValue(inputStream, new TypeReference<List<Article>>(){});

        model.addAttribute("articles", articles);
        return "articles";
    }
}
