package org.example.ee.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ee.core.model.User;
import org.example.ee.core.service.UserService;
import org.example.ee.core.util.Encryption;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/register")
public class Register extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String nic = request.getParameter("nic");
        String password = request.getParameter("password");

        String verificationCode = UUID.randomUUID().toString();
        String encrypt = Encryption.encrypt(password);

        String name = firstName + " " + lastName;

        User user = new User(nic,name,contact, email, encrypt,verificationCode);


        userService.registerUser(user);

        System.out.println("Registered user: " + user);

        response.sendRedirect(request.getContextPath()+"/registration-successful.jsp");
    }
}
