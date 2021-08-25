package com.ophthalmologist.clinic.web;

import com.ophthalmologist.clinic.models.HealthCard;
import com.ophthalmologist.clinic.services.HealthCardManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class HealthCardsController {

    private final HealthCardManager healthCardManager;

    @Autowired
    public HealthCardsController(HealthCardManager healthCardManager) {
        this.healthCardManager = healthCardManager;
    }
    @CrossOrigin(origins = "http://localhost:4400")
    @PostMapping("/add")
    public HealthCard add(@RequestBody HealthCard healthCard){
        return healthCardManager.save(healthCard);
    }

    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping("/{id}")
    public List<CardDTO> get(@PathVariable Integer id) throws ParseException {
        List<CardDTO>cardDTOS = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(healthCardManager.healthCards(id).get(0).getDate());
        List<HealthCard> cards=healthCardManager.healthCards(id);
        cards.sort(Comparator.comparing(HealthCard::getDate));
        for (HealthCard card:cards){
            cardDTOS.add(new CardDTO(card.getHealthCardId(),card.getPatientId(),card.getMedicines(),card.getDiseases(),card.getAllergies(),card.getBleeding(),simpleDateFormat.format(card.getDate())));
        }
        return cardDTOS;
    }


}
