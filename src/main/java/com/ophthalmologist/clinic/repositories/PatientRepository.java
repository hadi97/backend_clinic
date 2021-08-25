package com.ophthalmologist.clinic.repositories;

import com.ophthalmologist.clinic.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findAll();
}
