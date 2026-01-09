package org.example.ee.core.service;

import org.example.ee.core.model.Customer;
import org.example.ee.core.model.User;

public interface CustomerService {
    Customer getCustomerByNic(String nic);
    Customer getCustomerByEmail(String email);
    void registerCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    boolean validate(String email, String password);
}
