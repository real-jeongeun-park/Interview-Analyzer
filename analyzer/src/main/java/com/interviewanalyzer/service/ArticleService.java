package com.interviewanalyzer.service;

import com.interviewanalyzer.repository.ArticleRepository;
import com.interviewanalyzer.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository repository;

    @Autowired
    public ArticleService(ArticleRepository repository){
        this.repository = repository;
    }

    // public Long comment(Article article){
        // 이전 댓글과 중복이 아니라면!
        // ArticleRepository.save(article);
        // return article.getId();
   //  }

   //  public List<Article> findArticles(){
        // ArticleRepository.findAll();
    // }

}
