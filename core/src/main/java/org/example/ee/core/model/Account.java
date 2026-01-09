package org.example.ee.core.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bankaccount")
@Cacheable(false)
public class Account implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private double balance = 0.0;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public Account() {
    }

    public Account(String accountNumber, double balance, Customer customer, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
