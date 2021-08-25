package com.ophthalmologist.clinic.web.visits;

import com.ophthalmologist.clinic.models.*;
import com.ophthalmologist.clinic.repositories.AccountRepository;
import com.ophthalmologist.clinic.repositories.HealthCardRepository;
import com.ophthalmologist.clinic.repositories.TreatmentRepository;
import com.ophthalmologist.clinic.services.DoctorManager;
import com.ophthalmologist.clinic.services.PatientManager;
import com.ophthalmologist.clinic.services.VisitManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/visits")
public class VisitController {
    private final VisitManager visitManager;
    private final PatientManager patientManager;
    private final TreatmentRepository treatmentRepository;
    private final HealthCardRepository repository;
    private final DoctorManager doctorManager;
    private final AccountRepository accountRepository;

    @Autowired
    public VisitController(VisitManager visitManager, DoctorManager doctorManager, PatientManager patientManager, TreatmentRepository treatmentRepository, HealthCardRepository healthCardRepository,AccountRepository accountRepository) {
        this.visitManager = visitManager;
        this.patientManager = patientManager;
        this.treatmentRepository = treatmentRepository;
        this.repository = healthCardRepository;
        this.doctorManager = doctorManager;
        this.accountRepository=accountRepository;
    }
    @CrossOrigin(origins = "http://localhost:4400")
    @PostMapping
    public Visit saveVisit(@RequestBody VisitDTO visitDTO) throws ParseException {

        Optional<Patient> p;
        p = patientManager.findByUsername(visitDTO.getUsername());
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MM-yyyy");
        System.out.println(visitDTO.getUsername() + "lol " +p.get().getPatientId()+ visitDTO.getVisitDate());
        //adding the new healthCard
        HealthCard card = new HealthCard(p.get().getPatientId(), visitDTO.getMedicines(), visitDTO.getDiseases(), visitDTO.getAllergies(), visitDTO.getBleeding(),sdf.parse(visitDTO.getVisitDate()));
        repository.save(card);

        String s = "H"+visitDTO.getPeriod().replace(":", "_");

        java.sql.Date visitDate=new java.sql.Date(sdf.parse(visitDTO.getVisitDate()).getTime());
        Optional<Treatment> treatment = treatmentRepository.findById(visitDTO.getTreatmentId());
        System.out.println("visit date : "+visitDate);
        Visit v = new Visit();
        v.setPatientId(p.get().getPatientId());
        v.setCanceled(false);
        v.setConfirmed(false);
        v.setDate(visitDate);
        v.setPeriod(Period.valueOf(s));
        v.setDuration(treatment.get().getDuration()); //const
        v.setTreatment(treatment.get().getTreatmentName());
        System.out.println("i mean a7a+"+v.getDate());
        return visitManager.save(v);
    }


    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping(value = "/getVisit/{id}")
    public DetailedVisit getVisit(@PathVariable Integer id) {
        Optional<Visit> v = visitManager.getVisitById(id);
        return new DetailedVisit(v.get().getVisitId(), doctorManager.getDoctorName(),patientManager.getPatientName(v.get().getPatientId()),v.get().getTreatment(), v.get().getDate(),v.get().getAdvice(),v.get().getNotes(),"Yes");
    }
    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping(value = "/all")
    public List<AdminVisit> getAll() {
        System.out.println("Are you even here bro????");
        List<AdminVisit>all=visitManager.getAll();
        for(AdminVisit adminVisit:all){
            System.out.println("Iam here canceleed? "+adminVisit.isCancelled());
        }
        return all;

    }

    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping(value = "/getPast/{username}")
    public List<DetailedVisit> getPast(@PathVariable String username){
        Integer userId = accountRepository.findAll().stream().filter(account -> account.getUsername().equals(username)).findAny().get().getAccountId();

        List<DetailedVisit> detailed = new ArrayList<>();
        List<Visit> allVisits = visitManager.getPastVisits(userId);
        for (Visit v : allVisits){
            detailed.add(new DetailedVisit(v.getVisitId(), doctorManager.getDoctorName(),patientManager.getPatientName(v.getPatientId()),v.getTreatment(), v.getDate(),v.getAdvice(),v.getNotes(),"Yes"));

        }

        return detailed;
    }
    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping(value = "/getComing/{username}")
    public List<DetailedVisit> getNew(@PathVariable String username){
        System.out.println("what the fuck u doing ??"+username);
        Integer userId = accountRepository.findAll().stream().filter(account -> account.getUsername().equals(username)).findAny().get().getAccountId();

        List<DetailedVisit> detailed = new ArrayList<>();
        List<Visit> allVisits = visitManager.getComingVisits(userId);
        for (Visit v : allVisits){
            String confirmed;
            if (v.isConfirmed())
                confirmed="Yes";
            else
                confirmed="No";
            
            detailed.add(new DetailedVisit(v.getVisitId(), doctorManager.getDoctorName(),patientManager.getPatientName(v.getPatientId()),v.getTreatment(), v.getDate(),v.getAdvice(),v.getNotes(),confirmed));

        }
        return detailed;
    }

    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping(value = "/getToBeConfirmed")
    public List<DetailedVisit> getConfirm(){

        List<DetailedVisit> detailed = new ArrayList<>();
        List<Visit> allVisits = visitManager.getToBeConfirmed();
        for (Visit v : allVisits){
            String confirmed;
            confirmed="No";
            detailed.add(new DetailedVisit(v.getVisitId(), doctorManager.getDoctorName(),patientManager.getPatientName(v.getPatientId()),v.getTreatment(), v.getDate(),v.getAdvice(),v.getNotes(),confirmed));

        }

        return detailed;
    }



    @CrossOrigin(origins = "http://localhost:4400")
    @DeleteMapping(value = "/{visitId}")
    public void delete (@PathVariable Integer visitId){
        visitManager.delete(visitId);
    }



    @CrossOrigin
    @PutMapping(value = "/cancel/{visitId}")
    public void cancel(@PathVariable Integer visitId){
        visitManager.cancel(visitId);
    }


    @CrossOrigin
    @PutMapping(value = "/update")
    public void update(@RequestParam Integer visitId,@RequestParam String advice,@RequestParam String notes){
        visitManager.update(visitId,advice,notes);
    }

    @CrossOrigin(origins = "http://localhost:4400")
    @PutMapping(value = "/confirm/{visitId}")
    public void confirm(@PathVariable Integer visitId){
        visitManager.confirmAppointment(visitId);
    }



}
