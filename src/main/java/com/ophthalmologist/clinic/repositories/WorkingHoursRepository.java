package com.ophthalmologist.clinic.repositories;

import com.ophthalmologist.clinic.models.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours,Integer> {
    List<WorkingHours> findAll();
}
