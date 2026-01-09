package org.example.ee.web.servlet.customer;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ee.core.service.TransferService;

import java.io.IOException;

@WebServlet("/customer/fund-transfer-scheduled")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"CUSTOMER"}))
public class FundTransferScheduled extends HttpServlet {

    @Inject
    private TransferService transferService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String from = request.getParameter("from");
        String to = request.getParameter("to");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String dateTimeStr = request.getParameter("datetime");

        try {
            transferService.fundTransferScheduled(from, to, amount,dateTimeStr);
            response.sendRedirect(request.getContextPath()+ "/customer/fund-transfer-scheduled.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
