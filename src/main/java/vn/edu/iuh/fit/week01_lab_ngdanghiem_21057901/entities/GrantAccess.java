package vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "grant_access")
public class GrantAccess {
    @EmbeddedId
    private GrantAccessId id;

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "role_id", nullable = false)
    private vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Role role;

    @MapsId("accountId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ColumnDefault("b'1'")
    @Column(name = "is_grant", nullable = false)
    private Boolean isGrant = false;

    @ColumnDefault("''")
    @Column(name = "note", length = 250)
    private String note;

    public GrantAccessId getId() {
        return id;
    }

    public void setId(GrantAccessId id) {
        this.id = id;
    }

    public vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Role getRole() {
        return role;
    }

    public void setRole(vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(Boolean isGrant) {
        this.isGrant = isGrant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public GrantAccess() {
    }

    public GrantAccess(GrantAccessId id, vn.edu.iuh.fit.week01_lab_ngdanghiem_21057901.entities.Role role, Account account, Boolean isGrant, String note) {
        this.id = id;
        this.role = role;
        this.account = account;
        this.isGrant = isGrant;
        this.note = note;
    }

    public GrantAccess(String roleId, String accountId, Boolean isGrant, String note) {
        this.id = new GrantAccessId(roleId, accountId);
        this.isGrant = isGrant;
        this.note = note;
    }

}