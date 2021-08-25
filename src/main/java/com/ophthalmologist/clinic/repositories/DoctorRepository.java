package com.ophthalmologist.clinic.repositories;

import com.ophthalmologist.clinic.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    List<Doctor> findAll();
}
