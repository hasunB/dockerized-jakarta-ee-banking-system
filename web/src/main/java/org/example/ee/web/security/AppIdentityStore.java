package org.example.ee.web.security;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import org.example.ee.core.model.Admin;
import org.example.ee.core.model.Customer;
import org.example.ee.core.model.User;
import org.example.ee.core.service.AdminService;
import org.example.ee.core.service.CustomerService;
import org.example.ee.core.service.UserService;

import java.util.Set;

@AppAuthStore
@ApplicationScoped
public class AppIdentityStore implements IdentityStore {

    @EJB
    private CustomerService customerService;

    @EJB
    private AdminService adminService;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential upc = (UsernamePasswordCredential) credential;

            String nic = upc.getCaller();
            String rawPassword = upc.getPasswordAsString();

//             1. Try Admin first
            if (adminService.validate(nic, rawPassword)) {
                Admin admin = adminService.getAdminByNic(nic);
                System.out.println("Admin login success: " + admin.getNic());
                return new CredentialValidationResult(admin.getNic(), Set.of(admin.getUserType().name())); // e.g., "ADMIN" or "SUPER_ADMIN"
            } else {
                System.out.println("admin error");
            }

            // 2. Then try User
            if (customerService.validate(nic, rawPassword)) {
                Customer customer = customerService.getCustomerByNic(nic);
                System.out.println("User login success: " + customer.getNic());
                return new CredentialValidationResult(customer.getNic(), Set.of(customer.getUserType().name())); // e.g., "CUSTOMER"
            } else {
                System.out.println("user error");
            }


        }

        return CredentialValidationResult.INVALID_RESULT;
    }
}
