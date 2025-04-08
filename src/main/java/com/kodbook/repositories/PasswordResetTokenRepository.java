package com.kodbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kodbook.entities.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}