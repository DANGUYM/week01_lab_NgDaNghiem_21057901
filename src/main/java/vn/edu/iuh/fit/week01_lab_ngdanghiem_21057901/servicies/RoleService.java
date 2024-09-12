package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.servicies;

import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Account;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.repositories.RoleRepository;

import java.util.List;

public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();

    public List<Account> getAccountsForRole(String roleId) {
        // Logic để lấy danh sách account theo roleId
        return null;
    }
}
