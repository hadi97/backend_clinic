package com.ophthalmologist.clinic.services;

import com.ophthalmologist.clinic.models.Account;
import com.ophthalmologist.clinic.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AccountManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountManager.class);

    private final AccountRepository accountRepository;
    private final Map<Integer, Account> accountsById;
    private final Map<String, Account> accountsByUsername;

    @Autowired
    public AccountManager(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.accountsById = accountRepository.findAll()
                .parallelStream()
                .collect(Collectors.toMap(Account::getAccountId, Function.identity()));
        this.accountsByUsername = accountsById.values()
                .parallelStream()
                .collect(Collectors.toMap(Account::getUsername, Function.identity()));
    }


    public Account save(Account account){
        return accountRepository.save(account);
    }


    public void registerAccount(String username, String password, String role) {
        if (accountsByUsername.containsKey(username) || accountRepository.findByUsername(username).isPresent()) {
            LOGGER.warn("Attempt to register existing username {}", username);
            throw new AccountExistsException(username);
        }
        Account account = new Account(username, password, role);
        account = accountRepository.save(account);
        putAccount(account);
    }


    public boolean changePassword(Integer accountId, String oldPassword, String newPassword) {
        Account account = getAccountByUserIdOrThrow(accountId);
        boolean updated = account.updatePassword(oldPassword, newPassword);
        if (updated) {
            accountRepository.save(account);
        }
        return updated;
    }


    public Account getAccountByUsernameOrThrow(String username){
        if (accountsByUsername.containsKey(username)) {
            return accountsByUsername.get(username);
        } else {
            Optional<Account> maybeAccount = accountRepository.findAll()
                    .stream()
                    .filter(account -> account.getUsername().equals(username))
                    .findAny();
            if (maybeAccount.isPresent()) {
                Account account = maybeAccount.get();
                putAccount(account);
                return account;
            } else {
                LOGGER.warn("Could not find account by username {}", username);
                return null;
            }
        }
    }

    private Account getAccountByUserIdOrThrow(Integer accountId) {
        Account account;
        if (!accountsById.containsKey(accountId)) {
            account = accountRepository.getOne(accountId);
            putAccount(account);
        } else {
            account = accountsById.get(accountId);
        }
        return account;
    }

    public Account authenticate(String username, String password) throws AccountNotFoundException {
        Account account = getAccountByUsernameOrThrow(username);
        if (account.passwordMatches(password)) {
            LOGGER.info("Successfully authenticated {}", username);
            return account;
        } else {
            LOGGER.info("Incorrect password for {}", username);
            throw new AccountNotFoundException(username);
        }
    }

    private void putAccount(Account account) {
        accountsByUsername.put(account.getUsername(), account);
        accountsById.put(account.getAccountId(), account);
    }



}
