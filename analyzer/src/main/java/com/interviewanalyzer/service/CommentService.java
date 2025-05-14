package com.interviewanalyzer.service;

import com.interviewanalyzer.domain.Comment;
import com.interviewanalyzer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository repository;

    @Autowired
    public CommentService(CommentRepository repository){
        this.repository = repository;
    }

    public void saveComment(Comment comment){
        repository.save(comment);
    }

    public void updateLikes(Comment comment){
        comment.setLikes(comment.getLikes()+1);
        repository.save(comment);
    }

    public List<Comment> findAllComments(){
        return repository.findAll();
    }

    public Optional<Comment> findOne(Long id){
        return repository.findById(id);
    }
}
