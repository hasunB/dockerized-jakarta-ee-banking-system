package org.example.ee.web.servlet.admin;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ee.core.service.AdminService;
import org.example.ee.core.service.TestService;

import java.io.IOException;

@WebServlet("/admin/test-interest")
public class TestInterestServlet extends HttpServlet {

    @Inject
    private TestService testService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        testService.testInterest();
        resp.getWriter().write("Interest applied manually for testing.");
    }
}

