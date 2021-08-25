package com.ophthalmologist.clinic.services;

import com.ophthalmologist.clinic.models.Doctor;

import com.ophthalmologist.clinic.repositories.AccountRepository;
import com.ophthalmologist.clinic.repositories.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorManager {
    private final DoctorRepository doctorRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public DoctorManager(DoctorRepository doctorRepository, AccountRepository accountRepository) {

        this.doctorRepository = doctorRepository;
        this.accountRepository = accountRepository;
    }

    public Doctor save(Doctor d) {
        return doctorRepository.save(d);
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    public String getDoctorName() {
        List<Doctor> doc = doctorRepository.findAll();

        int id=doc.get(0).getAccountId();

        return accountRepository.findById(id).get().getFirstName() + " " + accountRepository.findById(id).get().getLastName();
    }

}
