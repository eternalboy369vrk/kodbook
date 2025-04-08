package com.kodbook.services;

import java.util.List;



import com.kodbook.entities.Comment;
import com.kodbook.entities.Post;


public interface CommentService {
    void saveComment(Comment comment);
    List<Comment> getComments(Post post);
}
