package org.example.ee.core.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
@NamedQueries({
        @NamedQuery(name = "Admin.findByEmail", query = "select u from Admin u where u.email =:email"),
        @NamedQuery(name = "Admin.findByNic", query = "select u from Admin u where u.nic =:nic"),
        @NamedQuery(name = "Admin.findByNicAndPassword",
                query = "select u from Admin u where u.nic=:nic and u.password=:password"),
})
@Cacheable(false)
public class Admin implements java.io.Serializable{

    @Id
    private String nic;
    private String name;
    private String contact;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.ADMIN;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public Admin() {
    }

    public Admin(String nic, String name, String contact, String email, String password) {
        this.nic = nic;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
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
}
