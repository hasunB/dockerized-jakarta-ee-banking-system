package org.example.ee.web.servlet.customer;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ee.core.service.TransferService;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/customer/fund-transfer")
//@ServletSecurity(@HttpConstraint(rolesAllowed = {"CUSTOMER"}))
public class FundTransfer extends HttpServlet {

    @Inject
    private TransferService transferService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String from = request.getParameter("from");
        String to = request.getParameter("to");
        double amount = Double.parseDouble(request.getParameter("amount"));

        try {
            transferService.fundTransfer(from, to, amount);
            response.sendRedirect(request.getContextPath()+ "/customer/fund-transfer-completed.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

