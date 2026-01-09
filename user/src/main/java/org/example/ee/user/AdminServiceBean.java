package org.example.ee.user;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import org.example.ee.core.annotation.Logged;
import org.example.ee.core.model.*;
import org.example.ee.core.service.AdminService;
import org.example.ee.core.service.CustomerService;
import org.example.ee.core.service.UserService;
import org.example.ee.core.util.Encryption;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Stateless
@Logged
public class AdminServiceBean implements AdminService {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private CustomerService customerService;

    @EJB
    private UserService userService;

    @Override
    public Admin getAdminByNic(String nic) {
        return em.createNamedQuery("Admin.findByNic", Admin.class)
                .setParameter("nic", nic).getSingleResult();
    }

    @Override
    public Admin getUserByEmail(String email) {
        return null;
    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void registerAdmin(Admin admin) {
        em.persist(admin);
    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void updateAdmin(Admin admin) {

    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void deleteAdmin(Admin Admin) {

    }

    @RolesAllowed({"ADMIN", "SUPER_ADMIN"})
    @Override
    public boolean approveUser(String nic) throws Exception {

        if (nic == null || nic.trim().isEmpty()) {
            throw new IllegalArgumentException("NIC cannot be null or empty");
        }

        User user = em.find(User.class, nic);

        if (user == null) {
            throw new Exception("User not found");
        }

        if (user.isApproved()) {
            throw new Exception("User is already approved");
        }

        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new Exception("User fields are incomplete");
        }

        user.setApproved(true);

        if (customerService == null) {
            throw new NullPointerException("CustomerService is not initialized");
        }

        // Create and register customer
        Customer customer = new Customer(
                user.getNic(),
                user.getName(),
                user.getContact(),
                user.getEmail(),
                user.getPassword(),
                UserType.CUSTOMER
        );

        customerService.registerCustomer(customer);

        // Create and persist account
        Account account = new Account(generateAccountNumber(),1000.0,customer,AccountType.SAVINGS);

        em.persist(account);
        userService.deleteUser(user);

        return true;
    }


    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void blockUser(Long nic) {

    }

    @Override
    public boolean validate(String nic, String password) {
        Admin admin = em.createNamedQuery("Admin.findByNic", Admin.class)
                .setParameter("nic", nic).getSingleResult();

        return admin != null && admin.getPassword().equals(password);
    }

    private String generateAccountNumber() {
        return "ACC" + new Random().nextInt(99999999);
    }
}
