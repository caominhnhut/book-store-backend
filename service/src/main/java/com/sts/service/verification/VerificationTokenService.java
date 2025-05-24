package com.sts.service.verification;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sts.entity.UserEntity;
import com.sts.entity.VerificationTokenEntity;
import com.sts.exception.ValidationException;
import com.sts.repository.VerificationTokenRepository;
import com.sts.util.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerificationTokenService {

    private static final int EXPIRATION_HOURS = 24;

    private final VerificationTokenRepository tokenRepository;

    public String createVerificationToken(UserEntity user) {
        String token = UUID.randomUUID().toString();

        VerificationTokenEntity verificationToken = VerificationTokenEntity.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusHours(EXPIRATION_HOURS))
                .build();

        tokenRepository.save(verificationToken);
        log.info("Verification token created for user ID: {}", user.getId());

        return token;
    }

    @Transactional
    public UserEntity validateToken(String token) {
        VerificationTokenEntity verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new ValidationException(ErrorCode.VERIFICATION_TOKEN_INVALID));

        if (verificationToken.isExpired()) {
            tokenRepository.delete(verificationToken);
            throw new ValidationException(ErrorCode.VERIFICATION_TOKEN_INVALID);
        }

        return verificationToken.getUser();
    }

    @Transactional
    public void deleteToken(String token) {
        tokenRepository.findByToken(token).ifPresent(tokenRepository::delete);
    }
}
