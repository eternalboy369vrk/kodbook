package com.kodbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entities.Notification;
import com.kodbook.entities.User;
import com.kodbook.repositories.NotificationRepository;

@Service
public class NotificationServiceImplementation implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    
    @Override
    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }
    
    @Override
    public List<Notification> getNotifications(User user) {
        return notificationRepository.findByUser(user);
    }
    
  
}
