package org.example.ee.core.service;

import org.example.ee.core.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccount(String nic);
}
