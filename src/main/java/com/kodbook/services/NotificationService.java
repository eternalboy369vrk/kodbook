package com.kodbook.services;

import java.util.List;


import com.kodbook.entities.Notification;
import com.kodbook.entities.User;


public interface NotificationService {
    void saveNotification(Notification notification);
    List<Notification> getNotifications(User user);
}
