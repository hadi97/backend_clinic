package com.ophthalmologist.clinic.models;


public class AccountRequest {

    private String login;
    private String password_;
    private String role;
    private String first_name;
    private String last_name;
    private boolean deleted;

    public AccountRequest(String login, String password_) {
        this.login = login;
        this.password_ = password_;
    }

    public AccountRequest setRole(String role) {
        this.role = role;
        return this;
    }

    public AccountRequest setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public AccountRequest setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public AccountRequest setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }


    public Account createUser() {
        return new Account(first_name, last_name,login, password_, role,deleted );
    }
}
