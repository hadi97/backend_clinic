package com.ophthalmologist.clinic.services;

import com.ophthalmologist.clinic.models.HealthCard;
import com.ophthalmologist.clinic.models.Visit;
import com.ophthalmologist.clinic.repositories.HealthCardRepository;
import com.ophthalmologist.clinic.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HealthCardManager {
    private final HealthCardRepository healthCardRepository;
    private final VisitRepository visitRepository;

    @Autowired
    public HealthCardManager(HealthCardRepository healthCardRepository,VisitRepository visitRepository) {
        this.healthCardRepository = healthCardRepository;
        this.visitRepository=visitRepository;
    }
public HealthCard save(HealthCard healthCard){
        return healthCardRepository.save(healthCard);
}


    public HealthCard healthCard(Integer patientId) {
        List<HealthCard> healthCards = healthCardRepository.findAll();

        return healthCards.stream()
                .filter(s -> s.getPatientId() == patientId)
                .findAny()
                .orElse(null);
    }

    public HealthCard updateHealthCard(HealthCard newHealthCard){
        HealthCard healthCard = healthCardRepository.getOne(newHealthCard.getHealthCardId());
        healthCard.setAllergies(newHealthCard.getAllergies());
        healthCard.setDiseases(newHealthCard.getDiseases());
        healthCard.setMedicines(newHealthCard.getMedicines());

        return healthCardRepository.save(healthCard);

    }
    public List<HealthCard> healthCards(Integer visitId) throws ParseException {
        Optional<Visit> visit = visitRepository.findById(visitId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<HealthCard> healthCards = healthCardRepository.findAll().stream().filter(healthCard -> healthCard.getPatientId()==visit.get().getPatientId()).collect(Collectors.toList());
        for (HealthCard healthCard:healthCards){
            String s = simpleDateFormat.format(healthCard.getDate());
            System.out.println("my string "+s);
            healthCard.setDate(simpleDateFormat.parse(s));
        }
        return healthCards;
    }


}
