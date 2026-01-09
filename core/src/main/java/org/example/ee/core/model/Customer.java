package org.example.ee.core.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "Customer.findByEmail", query = "select u from Customer u where u.email =:email"),
        @NamedQuery(name = "Customer.findByNic", query = "select u from Customer u where u.nic=:nic"),
        @NamedQuery(name = "Customer.findByNicAndPassword",
                query = "select u from Customer u where u.nic=:nic and u.password=:password"),
})
@Cacheable(false)
public class Customer implements java.io.Serializable {
    @Id
    private String nic;
    private String name;
    private String contact;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.USER;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts = new ArrayList<Account>();

    public Customer() {
    }

    public Customer(String nic, String name, String contact, String email, String password, UserType userType) {
        this.nic = nic;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
