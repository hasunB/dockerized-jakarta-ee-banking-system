package org.example.ee.core.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email =:email"),
        @NamedQuery(name = "User.findByNic", query = "select u from User u where u.nic=:nic"),
        @NamedQuery(name = "User.findByNicAndPassword",
                query = "select u from User u where u.nic=:nic and u.password=:password"),
})
@Cacheable(false)
public class User implements java.io.Serializable{
    @Id
    private String nic;
    private String name;
    private String contact;
    @Column(unique = true)
    private String email;
    private String password;
    private String verificationCode;
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.USER;

    private boolean approved = false;

    public User() {

    }

    public User(String nic, String name, String contact, String email, String password, String verificationCode) {
        this.nic = nic;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
