package org.example.ee.core.service;

import org.example.ee.core.model.Admin;
import org.example.ee.core.model.User;

import java.util.Collection;
import java.util.List;

public interface AdminService {
    Admin getAdminByNic(String nic);
    Admin getUserByEmail(String email);
    void registerAdmin(Admin admin);
    void updateAdmin(Admin admin);
    void deleteAdmin(Admin admin);
    boolean approveUser(String nic) throws Exception;
    void blockUser(Long nic);
    boolean validate(String nic, String password);
    List<User> getAllUsers();
}
