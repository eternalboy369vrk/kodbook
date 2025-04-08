package com.kodbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodbook.entities.Post;
import com.kodbook.entities.PostLike;
import com.kodbook.entities.User;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    
    PostLike findByPostAndUser(Post post, User user);
}
