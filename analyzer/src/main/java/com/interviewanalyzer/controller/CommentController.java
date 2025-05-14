package com.interviewanalyzer.controller;

import com.interviewanalyzer.domain.Comment;
import com.interviewanalyzer.dto.CommentForm;
import com.interviewanalyzer.dto.IdForm;
import com.interviewanalyzer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/home/community")
    public String community(Model model){
        List<Comment> comments = commentService.findAllComments();
        model.addAttribute("comments", comments);
        return "community";
    }

    @PostMapping("/home/community")
    public String create(CommentForm form){
        Comment comment = new Comment();
        comment.setContent(form.getContent());
        comment.setDate(getFormatDate());
        comment.setLikes(0L);

        commentService.saveComment(comment);

        return "redirect:community";
    }

    private String getFormatDate(){
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
    }

    @PostMapping("/home/community/like")
    public String update(IdForm form){
        System.out.println("form.id = " + form.getId());
        Optional<Comment> result = commentService.findOne(form.getId());
        result.ifPresent(c -> {
            commentService.updateLikes(c);
        });

        return "redirect:/home/community";
    }
}
