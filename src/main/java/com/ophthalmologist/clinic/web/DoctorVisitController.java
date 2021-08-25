package com.ophthalmologist.clinic.web;

import com.ophthalmologist.clinic.web.visits.Period;
import com.ophthalmologist.clinic.models.Visit;
import com.ophthalmologist.clinic.services.VisitManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/doctor")
public class DoctorVisitController {
    private final VisitManager visitManager;

    @Autowired
    public DoctorVisitController(VisitManager visitManager) {
        this.visitManager = visitManager;
    }

    @PutMapping(value = "/confirmVisit/{visitId}")
    public Visit confirmVisit(@PathVariable Integer visitId) {
        return visitManager.confirmAppointment(visitId);
    }

    @PutMapping(value = "/cancelVisit/{visitId}")
    public Visit cancelVisit(@PathVariable Integer visitId) {
        return visitManager.cancelAppointment(visitId);
    }

    @PutMapping(value = "/updateVisit/{visitId}")
    public Visit confirmVisit(@PathVariable Integer visitId, @RequestParam String notes, @RequestParam String advice, @RequestParam boolean isCompleted, @RequestParam int duration) {
        return visitManager.updateVisit(visitId, isCompleted, duration, notes, advice);
    }

    @PostMapping(value = "/makeVisit/{doctorId}")
    public  Visit makeVisit(@PathVariable Integer doctorId, @RequestParam Integer patientId, @RequestParam Date date, @RequestParam Period period, @RequestParam String treatment){
        return visitManager.makeAppointment(patientId,doctorId,date,period,treatment);
    }

    @GetMapping("/visits/{patientId}")
    public List<Visit> getVisits(@PathVariable Integer patientId){
        return visitManager.getAllVisits(patientId);
    }


}
