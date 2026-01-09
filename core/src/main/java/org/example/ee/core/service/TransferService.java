package org.example.ee.core.service;

import org.example.ee.core.model.TransactionLog;

import java.util.List;

public interface TransferService {
    void fundTransfer(String fromAccount, String toAccount, double amount) throws Exception;
    void fundTransferScheduled(String fromAccount, String toAccount, double amount, String dateTimeStr) throws Exception;
    List<TransactionLog> getCustomerTransactionLogs(String account) throws Exception;
    //void logAudit(String message);
}
