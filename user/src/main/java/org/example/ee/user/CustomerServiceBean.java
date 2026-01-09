package org.example.ee.user;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.persistence.Cacheable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.ee.core.annotation.Logged;
import org.example.ee.core.model.Customer;
import org.example.ee.core.model.User;
import org.example.ee.core.service.CustomerService;

@Stateless
@Logged
public class CustomerServiceBean implements CustomerService {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Customer getCustomerByNic(String nic) {
        return em.createNamedQuery("Customer.findByNic", Customer.class)
                .setParameter("nic", nic).getSingleResult();
    }


    @Override
    public Customer getCustomerByEmail(String email) {
        return null;
    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void registerCustomer(Customer customer) {
        em.persist(customer);
    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void updateCustomer(Customer customer) {

    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void deleteCustomer(Customer customer) {

    }

//    @RolesAllowed({"CUSTOMER","ADMIN","SUPER_ADMIN"})
    @Override
    public boolean validate(String nic, String password) {
        Customer customer = em.createNamedQuery("Customer.findByNic", Customer.class)
                .setParameter("nic", nic).getSingleResult();

        return customer != null && customer.getPassword().equals(password);
    }
}
