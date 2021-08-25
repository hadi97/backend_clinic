package com.ophthalmologist.clinic.services;

import com.ophthalmologist.clinic.models.Treatment;
import com.ophthalmologist.clinic.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentManager {

    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentManager(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<Treatment> getAll() {
        return treatmentRepository.findAll();
    }

    public Optional<Treatment> getById(Integer id) {
        return treatmentRepository.findById(id);
    }

    public void delete(Integer id) {
        treatmentRepository.deleteById(id);
    }

    public Treatment save(Treatment treatment){
        return treatmentRepository.save(treatment);
    }

}
