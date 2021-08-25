package com.ophthalmologist.clinic.repositories;

import com.ophthalmologist.clinic.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    List<Account> findAll();
    Optional <Account> findByUsername(String username);
}
