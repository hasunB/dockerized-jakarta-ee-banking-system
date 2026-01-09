package org.example.ee.web.servlet;

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
import org.example.ee.core.annotation.Logged;
import org.example.ee.core.exception.LoginFailedException;
import org.example.ee.core.util.Encryption;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Inject
    private SecurityContext securityContext;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nic = request.getParameter("nic");
        String password = request.getParameter("password");

        AuthenticationParameters parameters = AuthenticationParameters.withParams()
                .credential(new UsernamePasswordCredential(nic, Encryption.encrypt(password)));

        AuthenticationStatus status = securityContext.authenticate(request, response, parameters);

//        System.out.println("Authentication Status : " + status);


        if (status == AuthenticationStatus.SUCCESS) {
            request.getSession().setAttribute("customerNic", nic);
            response.sendRedirect(request.getContextPath()+ "/customer/");
        }else  {
            throw new LoginFailedException("Invalid email or password");
        }

    }
}

