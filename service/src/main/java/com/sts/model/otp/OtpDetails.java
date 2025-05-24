package com.sts.model.otp;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record OtpDetails(String otp, LocalDateTime expiryTime){

}
