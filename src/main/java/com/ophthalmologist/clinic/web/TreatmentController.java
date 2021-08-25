package com.ophthalmologist.clinic.web;

import com.ophthalmologist.clinic.models.Treatment;
import com.ophthalmologist.clinic.services.TreatmentManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping(value = "/treatments")
public class TreatmentController {

    private final TreatmentManager treatmentManager;

    public TreatmentController(TreatmentManager treatmentManager) {
        this.treatmentManager = treatmentManager;
    }
    @CrossOrigin
    @GetMapping
    public List<Treatment> getAll(){
        return treatmentManager.getAll();
    }


    @CrossOrigin
    @DeleteMapping("/{treatmentId}")
    public void deleteTreatment(@PathVariable Integer treatmentId) {
        treatmentManager.delete(treatmentId);
    }

    @CrossOrigin
    @PostMapping
    public Treatment createTreatment(@RequestBody Treatment t) {
        return treatmentManager.save(t);

    }

}
