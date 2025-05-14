package com.interviewanalyzer.repository;

import com.interviewanalyzer.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository{
    Comment save(Comment comment);
    List<Comment> findAll();
    Optional<Comment> findById(Long id);
}