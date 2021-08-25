package com.ophthalmologist.clinic.services;

import com.ophthalmologist.clinic.models.WorkingHours;
import com.ophthalmologist.clinic.repositories.WorkingHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class WorkingHoursManager {
    private WorkingHoursRepository workingHoursRepository;

    @Autowired
    public WorkingHoursManager(WorkingHoursRepository workingHoursRepository) {
        this.workingHoursRepository = workingHoursRepository;
    }


    public List<WorkingHours> getAllWorkingHours() {

        List<WorkingHours> working = workingHoursRepository.findAll();
        working.sort(Comparator.comparing(WorkingHours::getStartDate));
        return working;

    }


    public void updateWorkingHours(WorkingHours newWorkingHours) {
        SimpleDateFormat day = new SimpleDateFormat("EEE, dd-MM-yyyy ", Locale.ENGLISH);
        WorkingHours workingHours = workingHoursRepository.getOne(newWorkingHours.getId());
        Date myDay1 = new Date(workingHours.getStartDate().getTime());
        Date myDay2 = new Date(workingHours.getEndDate().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDay1);
        System.out.println("working hours for which day: "+calendar.get(Calendar.DAY_OF_WEEK));
        for (int i = 0; i <= 26; i++) {
            workingHours.setStartDate(new Timestamp(myDay1.getTime()));
            workingHours.setStartDate(new Timestamp(myDay2.getTime()));
            workingHoursRepository.save(workingHours);
            myDay1=new Date(myDay1.getTime()+TimeUnit.DAYS.toMillis(7));
            myDay2=new Date(myDay2.getTime()+TimeUnit.DAYS.toMillis(7));
        }

        workingHours.setStartDate(newWorkingHours.getStartDate());
        workingHours.setEndDate(newWorkingHours.getEndDate());

    }

    public WorkingHours add(WorkingHours workingHours) {
        return workingHoursRepository.save(workingHours);
    }
}
