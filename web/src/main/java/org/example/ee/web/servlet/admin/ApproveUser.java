package org.example.ee.web.servlet.admin;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ee.core.service.AdminService;

import java.io.IOException;

@WebServlet("/admin/approve-user")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN", "SUPER_ADMIN"}))
public class ApproveUser extends HttpServlet {

    @Inject
    private AdminService adminService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nic = request.getParameter("nic");
        try {
            boolean isApproved = adminService.approveUser(nic);

            if (isApproved) {
                System.out.println(nic +"Approved");
            } else {
                System.out.println(nic +"Not Approved");
            }



        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

