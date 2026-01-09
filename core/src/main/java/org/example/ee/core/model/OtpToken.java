package org.example.ee.core.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "otptoken")
@Cacheable(false)
public class OtpToken implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nic;
    private String otp;
    private LocalDateTime expiryTime;

    public OtpToken() {
    }

    public OtpToken(String nic, String otp, LocalDateTime expiryTime) {
        this.nic = nic;
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}
