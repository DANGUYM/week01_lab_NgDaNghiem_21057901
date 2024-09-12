package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.servicies;

import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Account;
import vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.repositories.AccountRepository;

import java.util.List;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();

    public Account login(String email, String password) {
        return accountRepository.findByEmailAndPassword(email, password);
    }

    public void addAccount(Account account) {
        accountRepository.addAccount(account);
    }

    public void updateAccount(Account account) {
        accountRepository.updateAccount(account);
    }

    public void deleteAccount(String accountId) {
        accountRepository.deleteAccount(accountId);
    }

    public Account getAccountById(String accountId) {
        return accountRepository.findById(accountId);
    }

    public boolean isAdmin(Account account) {
        // Logic kiểm tra account có phải admin không (kiểm tra role)
        return false; // Tạm thời trả về false
    }

    public List<String> getRolesForAccount(Account account) {
        // Logic để lấy danh sách quyền của một account
        return null;
    }

    public void grantRole(String accountId, String roleId) {
        // Logic cấp quyền cho account
    }
}

