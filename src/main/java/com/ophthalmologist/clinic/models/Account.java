package com.ophthalmologist.clinic.models;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    private String firstName;
    private String lastName;
    private String username;
    private String passwordHash;
    private String role;
    private boolean deleted;

    public Account() {

    }

    Account(Account account) {
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.username = account.getUsername();
        this.passwordHash = account.getPasswordHash();
        this.role = account.getRole();
        this.accountId = account.getAccountId();

    }

    public Account(String firstName, String lastName, String username, String passwordHash, String role,boolean deleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.deleted= false;
    }

    public Account(String username, String passwordHash, String role) {
        String salt = BCrypt.gensalt();
        this.username = username;
        this.passwordHash = BCrypt.hashpw(passwordHash, salt);
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String roles) {
        this.role = roles;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean updatePassword(String oldPassword, String newPassword) {
        if (!passwordMatches(oldPassword)) {
            return false;
        }
        setPassword(newPassword);
        return true;
    }

    void setPassword(String newPassword) {
        String salt = BCrypt.gensalt();
        this.passwordHash = BCrypt.hashpw(newPassword, salt);
    }

    public boolean passwordMatches(String password) {
        return BCrypt.checkpw(password, passwordHash);
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", roles=" + role +
                '}';
    }
}
