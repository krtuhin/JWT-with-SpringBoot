package com.jwt.services;

import com.jwt.models.RefreshToken;
import com.jwt.models.User;
import com.jwt.repo.RefreshTokenRepository;
import com.jwt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    //refresh token expiry time in ms
    public long refreshTokenExpiry = 12 * 60 * 60 * 1000;

    @Autowired
    private RefreshTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    //creating refresh token
    public RefreshToken createRefreshToken(String userName) {

        //fetch user from database
        User user = this.userRepository.findByUserEmail(userName);

        //fetch refresh token from user
        RefreshToken refreshToken = user.getRefreshToken();

        //if refresh token not generated then create refresh token
        if (refreshToken == null) {

            //creating refresh token
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expiry(Instant.now().plusMillis(this.refreshTokenExpiry))
                    .user(user).build();
        } else {
            //if refresh token already generated then extend the refresh token expiry
            refreshToken.setExpiry(Instant.now().plusMillis(this.refreshTokenExpiry));
        }

        user.setRefreshToken(refreshToken);

        //save token into database
        this.tokenRepository.save(refreshToken);

        return refreshToken;
    }

    //verify the refresh token expired or not
    public RefreshToken verifyRefreshToken(String refreshToken) {

        //fetch refresh token from database
        RefreshToken refreshTokenOb = this.tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Given token does not exist...!"));

        //verify token expiration
        if (refreshTokenOb.getExpiry().compareTo(Instant.now()) < 0) {

            //if refresh token expired then delete from database
            this.tokenRepository.delete(refreshTokenOb);

            throw new RuntimeException("Refresh token expired..!");
        } else {
            return refreshTokenOb;
        }
    }
}
