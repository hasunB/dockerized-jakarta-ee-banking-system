package org.example.ee.core.service;

public interface OtpService {
    String generateOtp(String nic);
    boolean validateOtp(String nic,String inputOpt);
}
