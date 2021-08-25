package com.ophthalmologist.clinic.repositories;

import com.ophthalmologist.clinic.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment,Integer> {
    List<Treatment> findAll();
}
