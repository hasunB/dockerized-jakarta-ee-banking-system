package org.example.ee.account;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.ee.core.model.Account;
import org.example.ee.core.model.User;
import org.example.ee.core.service.AccountService;

import java.util.List;

@Stateless
public class AccountServiceBean implements AccountService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Account> getAccount(String nic) {
        return em.createQuery("SELECT u FROM Account u where u.customer.nic =:nic", Account.class).setParameter("nic", nic).getResultList();
    }
}
