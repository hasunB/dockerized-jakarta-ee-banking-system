package org.example.ee.user;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.ee.core.annotation.Logged;
import org.example.ee.core.model.User;
import org.example.ee.core.service.UserService;

@Stateless
@Logged
public class UserServiceBean implements UserService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User getUserByNic(String nic) {
        return em.createNamedQuery("User.findByNic", User.class)
                .setParameter("nic", nic).getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return em.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email).getSingleResult();
    }

    @PermitAll
    @Override
    public void registerUser(User user) {
        em.persist(user);
    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void updateUser(User user) {

    }

    @RolesAllowed({"ADMIN","SUPER_ADMIN"})
    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }

    @Override
    public boolean validate(String nic, String password) {
        User user = em.createNamedQuery("User.findByNic", User.class)
                .setParameter("nic", nic).getSingleResult();

        return user != null && user.getPassword().equals(password);
    }
}

