package org.example.ee.auth;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.ee.core.service.OtpService;
import org.example.ee.core.model.OtpToken;

import java.time.LocalDateTime;
import java.util.Random;

@Stateless
public class OtpServiceBean implements OtpService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String generateOtp(String nic) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000)); // 6-digit OTP

        OtpToken token = new OtpToken();
        token.setNic(nic);
        token.setOtp(otp);
        token.setExpiryTime(LocalDateTime.now().plusMinutes(5)); // OTP valid for 5 mins

        em.persist(token);

        // TODO: Send OTP to email
        System.out.println("OTP for admin login: " + otp);

        return otp;
    }

    @Override
    public boolean validateOtp(String nic, String inputOtp) {
        try {
            OtpToken token = em.createQuery(
                            "SELECT t FROM OtpToken t WHERE t.nic = :nic ORDER BY t.expiryTime DESC", OtpToken.class)
                    .setParameter("nic", nic)
                    .setMaxResults(1)
                    .getSingleResult();

            return token.getOtp().equals(inputOtp) && LocalDateTime.now().isBefore(token.getExpiryTime());
        } catch (NoResultException e) {
            return false;
        }
    }
}
