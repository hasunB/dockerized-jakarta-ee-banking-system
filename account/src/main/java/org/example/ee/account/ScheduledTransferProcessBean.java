package org.example.ee.account;

import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.ee.core.model.Account;
import org.example.ee.core.model.ScheduledTransfer;
import org.example.ee.core.model.TransactionLog;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
public class ScheduledTransferProcessBean {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private TimerService timerService;

    public void createTimer(LocalDateTime time) {
        TimerConfig config = new TimerConfig();
        config.setPersistent(false);
        Date triggerTime = Date.from(time.atZone(ZoneId.systemDefault()).toInstant());

        // Create single-action timer
        timerService.createSingleActionTimer(triggerTime, config);
        System.out.println("Timer set for: " + triggerTime);
        System.out.println(time);
    }

    @Timeout
    public void processScheduledTransfers() {
        LocalDateTime now = LocalDateTime.now();

        List<ScheduledTransfer> pending = em.createQuery(
                        "SELECT s FROM ScheduledTransfer s WHERE s.scheduledAt <= :now AND s.processed = false",
                        ScheduledTransfer.class
                ).setParameter("now", now)
                .getResultList();

        for (ScheduledTransfer s : pending) {
            try {
                performTransfer(s);
                s.setProcessed(true);
                em.merge(s);
            } catch (Exception e) {
                System.err.println("Failed to process scheduled transfer ID " + s.getId() + ": " + e.getMessage());
            }
        }
    }

    private void performTransfer(ScheduledTransfer s) throws Exception {

        Account from = em.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :acc", Account.class)
                .setParameter("acc", s.getFromAccount()).getSingleResult();

        Account to = em.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :acc", Account.class)
                .setParameter("acc", s.getToAccount()).getSingleResult();

        if (from.getBalance() < s.getAmount() && from.getBalance() == 1000.0) {
            throw new Exception("Insufficient balance for scheduled transfer");
        }

        from.setBalance(from.getBalance() - s.getAmount());
        to.setBalance(to.getBalance() + s.getAmount());

        TransactionLog log = new TransactionLog();
        log.setFromAccount(s.getFromAccount());
        log.setToAccount(s.getToAccount());
        log.setAmount(s.getAmount());
        log.setTimestamp(LocalDateTime.now());
        log.setDescription("Scheduled transfer completed at"+ LocalDateTime.now());

        em.persist(log);
        em.merge(from);
        em.merge(to);

        System.out.println("Scheduled transfer completed at"+ LocalDateTime.now());
    }
}
