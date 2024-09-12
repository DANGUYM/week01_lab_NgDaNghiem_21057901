package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Account;

import java.util.List;

public class AccountRepository {
    private EntityManager em;

    public AccountRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
        em = emf.createEntityManager();
    }

    public Account findByEmailAndPassword(String email, String password) {
        TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.email = :email AND a.password = :password", Account.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

    public Account findById(String accountId) {
        return em.find(Account.class, accountId);
    }

    public void addAccount(Account account) {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }

    public void updateAccount(Account account) {
        em.getTransaction().begin();
        em.merge(account);
        em.getTransaction().commit();
    }

    public void deleteAccount(String accountId) {
        Account account = findById(accountId);
        if (account != null) {
            em.getTransaction().begin();
            em.remove(account);
            em.getTransaction().commit();
        }
    }

    public List<Account> findAll() {
        return em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }
}

