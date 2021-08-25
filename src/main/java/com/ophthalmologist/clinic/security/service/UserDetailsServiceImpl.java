package com.ophthalmologist.clinic.security.service;

import com.ophthalmologist.clinic.models.Account;
import com.ophthalmologist.clinic.services.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountManager accountManager;

    @Autowired
    public UserDetailsServiceImpl(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = null;
        try {
            account = accountManager.getAccountByUsernameOrThrow(s);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        assert account != null;
        return CustomerUserDetails.build(account);
    }
}
