package com.ophthalmologist.clinic.web.auth;

import com.ophthalmologist.clinic.models.Account;
import com.ophthalmologist.clinic.models.AccountRequest;
import com.ophthalmologist.clinic.models.Patient;
import com.ophthalmologist.clinic.security.ResponseMessage;
import com.ophthalmologist.clinic.security.jwt.JwtProvider;
import com.ophthalmologist.clinic.security.JwtResponse;
import com.ophthalmologist.clinic.security.service.LoginForm;
import com.ophthalmologist.clinic.security.service.SignUpForm;
import com.ophthalmologist.clinic.services.AccountManager;
import com.ophthalmologist.clinic.services.PatientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/auth")
public class AccountController {

    private final AccountManager accountManager;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final PatientManager patientManager;

    private final JwtProvider jwtProvider;

    @Autowired
    public AccountController(AccountManager accountManager, AuthenticationManager authenticationManager, PasswordEncoder encoder, PatientManager patientManager, JwtProvider jwtProvider) {
        this.accountManager = accountManager;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.patientManager = patientManager;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping(value = "/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest)  {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        int userId = accountManager.getAccountByUsernameOrThrow(loginRequest.getUsername()).getAccountId();
        System.out.println(userId);
        System.out.println(jwt);
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userId, userDetails.getAuthorities()));
    }

    @Transactional
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerAccount(@RequestBody SignUpForm signUpRequest) {
        if (accountManager.getAccountByUsernameOrThrow(signUpRequest.getUsername()) != null) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Login/Email is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Account user = new AccountRequest(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()))
                .setFirst_name(signUpRequest.getFirstName())
                .setLast_name(signUpRequest.getLastName())
                .setRole("patient")
                .createUser();


        accountManager.save(user);
        user = accountManager.getAccountByUsernameOrThrow(signUpRequest.getUsername());
        // Creating user's account


        patientManager.save(new Patient(signUpRequest.getAddress(), signUpRequest.getPhone(), signUpRequest.getPesel(), user.getAccountId(), signUpRequest.getSex()));

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);

    }

    @RequestMapping(value = "/changePassword/{accountId}/password", method = RequestMethod.POST)
    public ResponseEntity changePassword(@PathVariable Integer accountId, @RequestParam String oldPassword,
                                         @RequestParam String newPassword) {
        boolean passwordChanged = accountManager.changePassword(accountId, oldPassword, newPassword);
        if (passwordChanged) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/registerAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody SignUpForm signUpRequest) {
        if (accountManager.getAccountByUsernameOrThrow(signUpRequest.getUsername()) != null) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Login/Email is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Account user = new AccountRequest(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()))
                .setFirst_name(signUpRequest.getFirstName())
                .setLast_name(signUpRequest.getLastName())
                .setRole("admin")
                .createUser();

        accountManager.save(user);
        // Creating user's account


        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }
}
