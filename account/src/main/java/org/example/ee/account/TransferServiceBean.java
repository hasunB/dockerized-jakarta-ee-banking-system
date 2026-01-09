package org.example.ee.account;

import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.ee.core.model.Account;
import org.example.ee.core.model.ScheduledTransfer;
import org.example.ee.core.model.TransactionLog;
import org.example.ee.core.model.User;
import org.example.ee.core.service.TransferService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Stateless
public class TransferServiceBean implements TransferService {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private ScheduledTransferProcessBean scheduledTransferProcessBean;

    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void fundTransfer(String fromAccount, String toAccount, double amount) throws Exception {

        if (fromAccount.equals(toAccount)) throw new Exception("Can't transfer to same account");
        if (amount <= 0) throw new Exception("Invalid amount");
        if (amount < 100) throw new Exception("amount must be grater than LKR100");

        Account from = em.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :acc", Account.class)
                .setParameter("acc", fromAccount)
                .getSingleResult();

        Account to = em.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :acc", Account.class)
                .setParameter("acc", toAccount)
                .getSingleResult();

        if (from.getBalance() < amount && from.getBalance() == 1000.0) throw new Exception("Insufficient balance");

        // Withdraw
        from.setBalance(from.getBalance() - amount);

        // Deposit
        to.setBalance(to.getBalance() + amount);

        // Log transaction
        TransactionLog log = new TransactionLog();
        log.setFromAccount(fromAccount);
        log.setToAccount(toAccount);
        log.setAmount(amount);
        log.setTimestamp(LocalDateTime.now());
        log.setDescription("Transfer funds "+ amount +" from " + fromAccount + " to " + toAccount);

        em.persist(log);
        em.merge(from);
        em.merge(to);

        System.out.println("Transfer completed!");
    }

    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void fundTransferScheduled(String fromAccount, String toAccount, double amount, String dateTimeStr) throws Exception {

        if (fromAccount.equals(toAccount)) throw new Exception("Can't transfer to same account");
        if (amount <= 0) throw new Exception("Invalid amount");
        if (amount < 100) throw new Exception("amount must be grater than LKR100");

        ScheduledTransfer s = new ScheduledTransfer();
        s.setFromAccount(fromAccount);
        s.setToAccount(toAccount);
        s.setAmount(amount);
        s.setScheduledAt(LocalDateTime.parse(dateTimeStr));

        em.persist(s);

        scheduledTransferProcessBean.createTimer(s.getScheduledAt());

        System.out.println("Transfer Scheduled at " + s.getScheduledAt());
    }

    @Override
    public List<TransactionLog> getCustomerTransactionLogs(String account) throws Exception {
        return em.createQuery("SELECT u FROM TransactionLog u WHERE u.fromAccount = :acc", TransactionLog.class)
                .setParameter("acc", account)
                .getResultList();
    }

    // Logging shouldn't participate in the main transaction
//    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
//    public void logAudit(String message) {
//        System.out.println("Audit: " + message);
//    }
}
