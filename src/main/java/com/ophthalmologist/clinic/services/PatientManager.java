package com.ophthalmologist.clinic.services;

import com.ophthalmologist.clinic.models.Doctor;
import com.ophthalmologist.clinic.models.Patient;
import com.ophthalmologist.clinic.models.Visit;
import com.ophthalmologist.clinic.repositories.AccountRepository;
import com.ophthalmologist.clinic.repositories.DoctorRepository;
import com.ophthalmologist.clinic.repositories.PatientRepository;
import com.ophthalmologist.clinic.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientManager {
    private final PatientRepository patientRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public PatientManager(PatientRepository patientRepository, AccountRepository accountRepository) {
        this.patientRepository = patientRepository;
        this.accountRepository = accountRepository;
    }



    public List<Patient> getAll(){
        return patientRepository.findAll();
    }

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }

    public Optional<Patient> findByPesel(String pesel){
        return patientRepository.findAll()
                .stream()
                .filter(patient -> patient.getPesel().equals(pesel))
                .findAny();

    }
    public Optional<Patient> findByUserId(Integer id){
        return patientRepository.findAll()
                .stream()
                .filter(patient -> patient.getUserId() == id)
                .findAny();

    }
    public Optional<Patient> findByUsername(String username){
        Integer userId = accountRepository.findAll().stream().filter(account -> account.getUsername().equals(username)).findAny().get().getAccountId();
        return patientRepository.findAll()
                .stream()
                .filter(patient -> patient.getUserId() == userId)
                .findAny();
    }

    public String getPatientName(Integer id){
        Optional<Patient> p = patientRepository.findById(id);
        int userId = p.get().getUserId();

        return accountRepository.findById(userId).get().getFirstName() + " " + accountRepository.findById(userId).get().getLastName();
    }
    public String getPhoneByPatientId(Integer id){
        return patientRepository.findById(id).get().getPhone();
    }
    public String getPhone(Integer id){
        return patientRepository.findAll().stream().filter(patient -> patient.getUserId()==id).findAny().get().getPhone();
    }


}
