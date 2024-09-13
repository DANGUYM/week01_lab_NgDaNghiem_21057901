package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Role;

import java.util.List;

public class RoleRepository {
    private EntityManager em;

    public RoleRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-mariadb");
        em = emf.createEntityManager();
    }

    public Role findById(String roleId) {
        return em.find(Role.class, roleId);
    }

    public List<Role> findAll() {
        return em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }
}
