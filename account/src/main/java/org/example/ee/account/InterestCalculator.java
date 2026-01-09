package org.example.ee.account;

import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.ee.core.model.Account;
import org.example.ee.core.model.AccountType;
import org.example.ee.core.model.TransactionLog;

import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class InterestCalculator {

    @PersistenceContext
    private EntityManager em;

    private static final double INTEREST_RATE = 0.03;

    @Schedule(hour = "0", minute = "0", persistent = false) //triggers every midnight (00.00 AM)
    public void applyDailyInterest() {
        System.out.println("Running nightly interest calculation...");

        List<Account> savingsAccounts = em.createQuery(
                        "SELECT a FROM Account a WHERE a.accountType = :type", Account.class)
                .setParameter("type", AccountType.SAVINGS)
                .getResultList();

        for (Account acc : savingsAccounts) {
            double interest = calculateDailyInterest(acc.getBalance());
            acc.setBalance(acc.getBalance() + interest);

            // Optional: log as a transaction
            TransactionLog log = new TransactionLog();
            log.setFromAccount("BANK");
            log.setToAccount(acc.getAccountNumber());
            log.setAmount(interest);
            log.setTimestamp(LocalDateTime.now());
            log.setDescription("Daily interest applied: LKR" + interest);

            em.persist(log);
            em.merge(acc);
        }

        System.out.println("Interest calculation completed.");
    }

    private double calculateDailyInterest(double balance) {
        return balance * (INTEREST_RATE / 365.0);
    }
}
