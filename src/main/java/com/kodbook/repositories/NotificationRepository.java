package com.kodbook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodbook.entities.Notification;
import com.kodbook.entities.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
    List<Notification> findByUser(User user);
}
