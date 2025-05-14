package com.interviewanalyzer.repository;

import com.interviewanalyzer.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaCommentRepository extends JpaRepository<Comment, Long>, CommentRepository{
}
