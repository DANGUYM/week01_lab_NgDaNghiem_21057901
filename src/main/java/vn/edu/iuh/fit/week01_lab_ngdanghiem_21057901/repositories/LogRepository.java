package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Log;

public class LogRepository {
    private EntityManager em;

    public LogRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
        em = emf.createEntityManager();
    }

    public void recordLog(Log log) {
        em.getTransaction().begin();
        em.persist(log);
        em.getTransaction().commit();
    }
}
