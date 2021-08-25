package com.ophthalmologist.clinic.repositories;

import com.ophthalmologist.clinic.models.HealthCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthCardRepository extends JpaRepository<HealthCard, Integer> {
    List<HealthCard> findAll();
}
