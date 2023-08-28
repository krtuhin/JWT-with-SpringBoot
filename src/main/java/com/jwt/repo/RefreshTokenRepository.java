package com.jwt.repo;

import com.jwt.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    //method to get refresh token from database
    public Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
