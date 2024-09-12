package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class GrantAccessId implements java.io.Serializable {
    private static final long serialVersionUID = 1803824775502689551L;
    @Column(name = "role_id", nullable = false, length = 50)
    private String roleId;

    @Column(name = "account_id", nullable = false, length = 50)
    private String accountId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GrantAccessId entity = (GrantAccessId) o;
        return Objects.equals(this.accountId, entity.accountId) &&
                Objects.equals(this.roleId, entity.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, roleId);
    }

    public GrantAccessId() {
    }

    public GrantAccessId(String roleId, String accountId) {
        this.roleId = roleId;
        this.accountId = accountId;
    }

    public GrantAccessId(String roleId, Account account) {
        this.roleId = roleId;
        this.accountId = account.getAccountId();
    }

}