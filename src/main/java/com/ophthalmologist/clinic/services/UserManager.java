package com.ophthalmologist.clinic.services;

import com.ophthalmologist.clinic.models.Account;
import com.ophthalmologist.clinic.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserManager {


    private final AccountRepository accountRepository;

    @Autowired
    public UserManager(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public List<Account> getAll(){
        return accountRepository.findAll();
    }
    public List<Account> getDoctors(){
        return accountRepository.findAll()
                .stream()
                .filter(account -> account.getRole().equalsIgnoreCase("doctor"))
                .collect(Collectors.toList());
    }
    public List<Account> getPatients(){
        return accountRepository.findAll()
                .stream()
                .filter(account -> account.getRole().equalsIgnoreCase("patient"))
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        accountRepository.deleteById(id);
    }

    public Account getUserName(String username){
         return  accountRepository.findAll().stream().filter(account -> account.getUsername().equals(username)).findAny().get();

    }



}
