package com.ophthalmologist.clinic.repositories;

import com.ophthalmologist.clinic.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit,Integer> {
    List<Visit> findAll();
    Visit save(Visit visit);

}
