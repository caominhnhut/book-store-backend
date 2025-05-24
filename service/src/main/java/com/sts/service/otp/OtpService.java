package com.sts.service.otp;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.sts.model.otp.OtpDetails;
import com.sts.service.notification.sms.SmsService;

@Service
@Log4j2
public class OtpService{

    private final SmsService smsService;

    private final Map<String, OtpDetails> otpStorage = new ConcurrentHashMap<>();

    private final Map<String, LocalDateTime> otpRequestTimestamps = new ConcurrentHashMap<>();

    public OtpService(SmsService smsService) {
        this.smsService = smsService;
    }

    public String generateOtp(String phoneNumber){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastRequestTime = otpRequestTimestamps.get(phoneNumber);

//        if(lastRequestTime != null && lastRequestTime.isAfter(now.minusHours(1))){
//            throw new IllegalStateException("OTP request limit exceeded for the last hour");
//        }

        String otp = String.format("%4d", new Random().nextInt(10000));
        LocalDateTime expiryTime = now.plusMinutes(60);
        otpStorage.put(phoneNumber, new OtpDetails(otp, expiryTime));
        otpRequestTimestamps.put(phoneNumber, now);

        //sendOtpSms(phoneNumber, otp);

        log.info("OTP {} generated for phone number: {}", otp, phoneNumber);

        return otp;
    }

    public boolean validateOtp(String phoneNumber, String otp){
        OtpDetails otpDetails = otpStorage.get(phoneNumber);
        if(otpDetails == null)
            return false;
        return otpDetails.otp().equals(otp);
    }

    private void sendOtpSms(String phoneNumber, String otp){
        String message = "Your OTP is: " + otp;
        smsService.sendSms(phoneNumber, message);
    }
}