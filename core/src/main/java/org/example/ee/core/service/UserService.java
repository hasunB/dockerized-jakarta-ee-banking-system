package org.example.ee.core.service;

import org.example.ee.core.model.User;

public interface UserService {
    User getUserByNic(String nic);
    User getUserByEmail(String email);
    void registerUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    boolean validate(String email, String password);
}
