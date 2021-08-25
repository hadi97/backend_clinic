package com.ophthalmologist.clinic.web.patient;

import com.ophthalmologist.clinic.models.Account;
import com.ophthalmologist.clinic.models.AccountRequest;
import com.ophthalmologist.clinic.models.Patient;
import com.ophthalmologist.clinic.services.AccountManager;
import com.ophthalmologist.clinic.services.PatientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/patients")

public class PatientController {
    private final PatientManager patientManager;
    private final PasswordEncoder encoder;
    private final AccountManager accountManager;

    @Autowired
    public PatientController(PatientManager patientManager, PasswordEncoder encoder,AccountManager accountManager) {
        this.patientManager = patientManager;
        this.encoder = encoder;
        this.accountManager=accountManager;
    }
    @CrossOrigin
    @GetMapping
    public List<Patient> getPatients() {
        return patientManager.getAll();
    }
    @CrossOrigin
    @PostMapping
    public Patient Save(@RequestBody PatientDTO patientDTO)  {


        Account user = new AccountRequest(patientDTO.getUsername(), encoder.encode(patientDTO.getPasswordHash()))
                .setFirst_name(patientDTO.getFirstName())
                .setLast_name(patientDTO.getLastName())
                .setRole("patient")
                .createUser();

        accountManager.save(user);
        Account savedPatient = accountManager.getAccountByUsernameOrThrow(patientDTO.getUsername());


        Patient patient = new Patient();
        patient.setMale(patientDTO.getSex());
        patient.setUserId(savedPatient.getAccountId());
        patient.setAddress(patientDTO.getAddress());
        patient.setPesel(patientDTO.getPesel());
        patient.setPhone(patientDTO.getPhone());

        return patientManager.save(patient);

    }


}
