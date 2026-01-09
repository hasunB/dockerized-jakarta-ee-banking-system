package org.example.ee.web.servlet;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ee.core.exception.LoginFailedException;
import org.example.ee.core.model.Admin;
import org.example.ee.core.service.AdminService;
import org.example.ee.core.service.OtpService;
import org.example.ee.core.util.Encryption;

import java.io.IOException;

@WebServlet("/admin-login")
public class adminLogin extends HttpServlet {

    @EJB
    private AdminService adminService;
    @Inject
    private OtpService otpService;

    @Inject
    private SecurityContext securityContext;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nic = request.getParameter("nic");
        String password = request.getParameter("password");

        boolean isAdmin = adminService.validate(nic, Encryption.encrypt(password));

        if (isAdmin) {
            otpService.generateOtp(nic); // generate OTP
            request.getSession().setAttribute("adminNic", nic);
            System.out.println("OTP generated");

            response.sendRedirect("verify-admin-otp.jsp");
        } else {
            throw new LoginFailedException("Invalid email or password");
        }

    }
}
