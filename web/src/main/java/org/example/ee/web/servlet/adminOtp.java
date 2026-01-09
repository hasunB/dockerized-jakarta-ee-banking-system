package org.example.ee.web.servlet;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
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

@WebServlet("/verify-admin-otp")
public class adminOtp extends HttpServlet {

    @EJB
    private AdminService adminService;
    @Inject
    private OtpService otpService;

    @Inject
    private SecurityContext securityContext;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String inputOtp = req.getParameter("num0") +
                req.getParameter("num1") +
                req.getParameter("num2") +
                req.getParameter("num3") +
                req.getParameter("num4") +
                req.getParameter("num5");

        String adminNic = (String) req.getSession().getAttribute("adminNic");

        if (otpService.validateOtp(adminNic, inputOtp)) {

            Admin admin = adminService.getAdminByNic(adminNic);
            AuthenticationParameters params = AuthenticationParameters.withParams()
                    .credential(new UsernamePasswordCredential(adminNic, admin.getPassword()));

            AuthenticationStatus status = securityContext.authenticate(req, resp, params);

            if (status == AuthenticationStatus.SUCCESS) {
                resp.sendRedirect(req.getContextPath() + "/admin/");
                req.getSession().setAttribute("adminName", admin.getName());
            } else {
                throw new LoginFailedException("2FA Login failed");
            }
        } else {
            throw new LoginFailedException("Invalid or expired OTP");
        }
    }
}
