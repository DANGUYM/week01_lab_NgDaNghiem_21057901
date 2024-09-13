package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Log;

public class LogRepository {
    private EntityManager em;

    public LogRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-mariadb");
        em = emf.createEntityManager();
    }

    public Log findMostRecentLogByAccountId(String accountId) {
        Log log = null;
        try {
            log = em.createQuery("SELECT l FROM Log l WHERE l.accountId = :accountId ORDER BY l.id DESC", Log.class)
                    .setParameter("accountId", accountId)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return log;
    }

    public boolean recordLog(Log log) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(log);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

//    public boolean recordLogout(Log updatedLog) {
//        EntityTransaction tx = em.getTransaction();
//        try {
//            tx.begin();
//            em.persist(updatedLog); // Lưu log vào CSDL
//            tx.commit();
//            return true;
//        } catch (Exception e) {
//            if (tx.isActive()) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        }
//        return false;
//    }

}
