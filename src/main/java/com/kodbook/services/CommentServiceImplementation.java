package com.kodbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entities.Comment;
import com.kodbook.entities.Post;
import com.kodbook.repositories.CommentRepository;

@Service
public class CommentServiceImplementation implements CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getComments(Post post) {
        return commentRepository.findByPost(post);
    }
}
