package com.ophthalmologist.clinic.services;

import com.ophthalmologist.clinic.models.*;
import com.ophthalmologist.clinic.repositories.*;
import com.ophthalmologist.clinic.web.freeHours.VisitInfo;
import com.ophthalmologist.clinic.web.visits.AdminVisit;
import com.ophthalmologist.clinic.web.visits.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VisitManager {
    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final WorkingHoursRepository workingHoursRepository;
    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;
    private final PatientManager patientManager;
    private final DoctorManager doctorManager;

    @Autowired

    public VisitManager(VisitRepository visitRepository, PatientManager patientManager, DoctorManager doctorManager, PatientRepository patientRepository, AccountRepository accountRepository, DoctorRepository doctorRepository, WorkingHoursRepository workingHoursRepository) {

        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
        this.workingHoursRepository = workingHoursRepository;
        this.accountRepository = accountRepository;
        this.patientRepository = patientRepository;
        this.patientManager = patientManager;
        this.doctorManager = doctorManager;
    }

    public Optional<Visit> getVisitById(Integer visit) {
        return visitRepository.findById(visit);
    }


    public Visit save(Visit visit) {

        System.out.println("tabe hena a7ene"+visit.getDate());
        return visitRepository.save(visit);
    }

    public Visit confirmAppointment(Integer visitId) {
        Visit visit = visitRepository.getOne(visitId);
        visit.setConfirmed(true);
        return visitRepository.save(visit);
    }


    public Visit cancelAppointment(Integer visitId) {
        Visit visit = visitRepository.getOne(visitId);
        visit.setCanceled(true);
        visit.setConfirmed(false);
        return visitRepository.save(visit);
    }

    public Visit makeAppointment(Integer patientId, Integer doctorId, Date date, Period period, String treatment) {
        Visit visit = new Visit(patientId, doctorId, date, period, treatment);
        return visitRepository.save(visit);
    }

    public Visit updateVisit(Integer visitId, boolean isCompleted, int duration, String notes, String advice) {
        Visit visit = visitRepository.getOne(visitId);
        visit.setCompleted(isCompleted);
        visit.setDuration(duration);
        visit.setNotes(notes);
        visit.setAdvice(advice);
        return visitRepository.save(visit);
    }

    public List<Visit> getAllVisits(Integer patientId) {
        List<Visit> visits = visitRepository.findAll();
        return visits.stream()
                .filter(v -> v.getPatientId() == patientId)
                .collect(Collectors.toList());
    }


    private Collection<? extends Period> preparePeriods(WorkingHours workingHour) {
        java.sql.Date date = new java.sql.Date(workingHour.getStartDate().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formattedStartHour = sdf.format(date);

        java.sql.Date endDate = new java.sql.Date(workingHour.getEndDate().getTime());
        SimpleDateFormat sdfEndHour = new SimpleDateFormat("HH:mm");
        String formattedENdHour = sdfEndHour.format(endDate);

        return Period.betweenHours(formattedStartHour, formattedENdHour);
    }

    public List<String> getDays() {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MM-yyyy");
        for (int i = 0; i < 14; i++) {
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.now().plusDays(i));
            for (WorkingHours workingHours : workingHoursRepository.findAll()) {
                if (simpleDateFormat.format(workingHours.getStartDate()).equals(simpleDateFormat.format(date)))
                    dateList.add(simpleDateFormat.format(date));
            }
        }
        return dateList;
    }


    public List<VisitInfo> availableVisits(int days) {

        Map<java.sql.Date, List<Period>> available = new HashMap<>();
        for (int i = 0; i < days; i++) {
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.now().plusDays(i));
            List<Period> dailySchedule = preparePeriodsForDoctor(date);
            //TO_DO per doctors hours
            //remove scheduled visits
            //visitMapper.findVisitsByDoctorAndDate(doc.getId(),date)
            available.put(date, dailySchedule);
        }
        List<VisitInfo> returnList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MM-yyyy");
        for (java.sql.Date day : available.keySet()) {
            List<Period> doctorListMap = available.get(day);
            for (Period p : doctorListMap) {
                VisitInfo v = new VisitInfo();
                v.setDate(simpleDateFormat.format(day));
                String docName = prepareDoctorNameByDoctorID();
                v.setDoctorName(docName);
                v.setPeriod(p.getHourName());
                v.setP(p);
                returnList.add(v);
            }
        }
        return returnList;
    }

    public List<Period> preparePeriodsForDoctor(java.util.Date date) {
        List<Period> periods = new ArrayList<>();
        List<WorkingHours> workingHours = get(date);
        for (WorkingHours workingHour : workingHours) {
            periods.addAll(preparePeriods(workingHour));
        }
        List <Period> taken= takenVisits(date);
        for (Period period : taken) {
            periods.remove(period);
        }
        return periods;
    }

    private String prepareDoctorNameByDoctorID() {
        Integer userId = doctorRepository.findAll().get(0).getAccountId();
        Optional<Account> doctor = accountRepository.findById(userId);
        return doctor.get().getFirstName() + " " + doctor.get().getLastName();
    }

    /*
        private List<Visit> getVisitsFromNow(Date to) {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            return visitRepository.findAll()
                    .stream()
                    .filter(v -> v.getDate().compareTo(to) < 0 && v.getDate().compareTo(date) > 0).collect(Collectors.toList());

        }
    */
    private List<WorkingHours> get(java.util.Date date) {

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = ft.format(date);

        return workingHoursRepository.findAll().stream().filter(workingHours -> ft.format(workingHours.getStartDate()).equals(date1))
                .collect(Collectors.toList());

    }

    public List<Period> takenVisits(java.util.Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Period> periods = new ArrayList<>();
        List<Visit> visits = visitRepository.findAll()
                .stream()
                .filter(visit -> !visit.isCanceled() && !visit.isCompleted() && simpleDateFormat.format(visit.getDate()).equals(simpleDateFormat.format(date))).collect(Collectors.toList());
        int i=1;
        for (Visit visit : visits) {
            periods.add(visit.getPeriod());
            System.out.println(simpleDateFormat.format(date)+" taken times["+ i++ +"] : " +visit.getPeriod().getHourName());
        }
        return periods;
    }


    public List<Visit> getComingVisits(Integer userId) {
        Account a = accountRepository.getOne(userId);
        Optional<Doctor> doctor;
        Optional<Patient> patient;
        if (a.getRole().equals("doctor")) {
            doctor = doctorRepository.findAll().stream().filter(doctor1 -> doctor1.getAccountId() == userId).findAny();
            return visitRepository.findAll()
                    .stream()
                    .filter(visit -> !visit.isCompleted() && !visit.isCanceled() && visit.isConfirmed())
                    .collect(Collectors.toList());
        } else {
            patient = patientRepository.findAll().stream().filter(patient1 -> patient1.getUserId() == userId).findAny();

            return visitRepository.findAll()
                    .stream()
                    .filter(visit -> visit.getPatientId() == patient.get().getPatientId() && !visit.isCompleted() && !visit.isCanceled())
                    .collect(Collectors.toList());
        }
    }


    public List<Visit> getToBeConfirmed(){

        return visitRepository.findAll()
                .stream()
                .filter(visit -> !visit.isCompleted() && !visit.isConfirmed() && !visit.isCanceled())
                .collect(Collectors.toList());

    }

    public List<Visit> getPastVisits(Integer userId) {
        Account a = accountRepository.getOne(userId);
        Optional<Doctor> doctor;
        Optional<Patient> patient;
        if (a.getRole().equals("doctor")) {
            doctor = doctorRepository.findAll().stream().filter(doctor1 -> doctor1.getAccountId() == userId).findAny();

            return visitRepository.findAll()
                    .stream()
                    .filter(visit -> !visit.isCanceled() && visit.isCompleted())
                    .collect(Collectors.toList());
        } else {
            patient = patientRepository.findAll().stream().filter(patient1 -> patient1.getUserId() == userId).findAny();
            return visitRepository.findAll()
                    .stream()
                    .filter(visit -> visit.getPatientId() == patient.get().getPatientId() && visit.isCompleted() && !visit.isCanceled())
                    .collect(Collectors.toList());

        }
    }

    public List<AdminVisit> getAll() {
        List<Visit> visits = visitRepository.findAll();
        List<AdminVisit> adminVisits = new ArrayList<>();
        for (Visit v : visits) {
            String s;
            if (!v.isCanceled())
                s = "Completed";
            else
                s = "Canceled";
            adminVisits.add(new AdminVisit(v.getVisitId(), s, patientManager.getPatientName(v.getPatientId()), s, v.getTreatment(), v.getDate()));
        }
        return adminVisits;
    }

    public void delete(Integer id) {
        visitRepository.deleteById(id);
    }

    public void cancel(Integer id) {
        Visit visit = visitRepository.getOne(id);
        visit.setCanceled(true);
        visitRepository.save(visit);
    }

    public void update(Integer id, String advice, String notes) {
        Visit visit = visitRepository.getOne(id);
        visit.setCompleted(true);
        visit.setAdvice(advice);
        visit.setNotes(notes);
        visitRepository.save(visit);
    }


}
