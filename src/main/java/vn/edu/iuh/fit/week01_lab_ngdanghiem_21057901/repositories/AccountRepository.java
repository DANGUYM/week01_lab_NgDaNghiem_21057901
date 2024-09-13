package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.repositories;

import jakarta.persistence.*;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Account;

public class AccountRepository {
    private EntityManager em;

    public AccountRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-mariadb");
        em = emf.createEntityManager();
    }

    public Account findByEmailAndPassword(String email, String password) {

        Account acc = null;
        EntityTransaction tx = null;

        try {

            tx = em.getTransaction();
            tx.begin();

            acc = em.createQuery("FROM Account WHERE email = :email AND password = :password", Account.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();

            tx.commit();

        } catch (Exception e) {
            if(tx!=null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return acc;
    }

//    public boolean isAdmin(Account account) {
//        try {
//            // Truy vấn JPQL để kiểm tra xem account có vai trò 'admin' không
//            String jpql = "SELECT r.roleName " +
//                    "FROM Account a " +
//                    "JOIN GrantAccess ga ON a.accountId = ga.account.accountId " +
//                    "JOIN Role r ON ga.id.roleId = r.roleId " +
//                    "WHERE a.accountId = :account_id AND ga.isGrant = true AND r.roleName = 'admin'";
//
//            // Thực thi truy vấn
//            String role = em.createQuery(jpql, String.class)
//                    .setParameter("account_id", account.getAccountId())
//                    .getSingleResult();
//
//            // Nếu tìm thấy role 'admin', trả về true
//            return "admin".equals(role);
//
//        } catch (NoResultException e) {
//            // Nếu không tìm thấy, trả về false
//            return false;
//        }
//    }


    public boolean isAdmin(Account account) {
        try {
            String jpql = "SELECT r.roleName " +
                    "FROM Account a " +
                    "JOIN GrantAccess ga ON a.accountId = ga.account.accountId " +
                    "JOIN Role r ON ga.id.roleId = r.roleId " +
                    "WHERE a.accountId = :account_id AND ga.isGrant = true AND r.roleName = 'administrator'";

            String role = em.createQuery(jpql, String.class)
                    .setParameter("account_id", account.getAccountId())
                    .getSingleResult();

            return "administrator".equals(role);

        } catch (NoResultException e) {
            return false;
        }
    }


    public static void main(String[] args) {
        // Test isAdmin
        AccountRepository accountRepository = new AccountRepository();
        Account account = accountRepository.findByEmailAndPassword("teo@gmail.com", "123");
        System.out.println(accountRepository.isAdmin(account));
    }

//    public Account findById(String accountId) {
//        return em.find(Account.class, accountId);
//    }
//
//    public void addAccount(Account account) {
//        em.getTransaction().begin();
//        em.persist(account);
//        em.getTransaction().commit();
//    }
//
//    public void updateAccount(Account account) {
//        em.getTransaction().begin();
//        em.merge(account);
//        em.getTransaction().commit();
//    }
//
//    public void deleteAccount(String accountId) {
//        Account account = findById(accountId);
//        if (account != null) {
//            em.getTransaction().begin();
//            em.remove(account);
//            em.getTransaction().commit();
//        }
//    }
//
//    public List<Account> findAll() {
//        return em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
//    }
}

