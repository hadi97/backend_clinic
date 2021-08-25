package com.ophthalmologist.clinic.web.workingHours;

import com.ophthalmologist.clinic.models.WorkingHours;
import com.ophthalmologist.clinic.services.WorkingHoursManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@CrossOrigin
@RestController
@RequestMapping(value = "/workingHours")
public class Controller {
    private final WorkingHoursManager workingHoursManager;

    @Autowired
    public Controller(WorkingHoursManager workingHoursManager) {
        this.workingHoursManager = workingHoursManager;
    }

    @GetMapping
    public List<WorkingHoursDTO> getAll() {
        List<WorkingHours> workingHours = workingHoursManager.getAllWorkingHours();
        List <WorkingHoursDTO> formDTOS = new ArrayList<>();
        SimpleDateFormat day = new SimpleDateFormat("EEE, dd-MM-yyyy ",Locale.ENGLISH);
        SimpleDateFormat hours = new SimpleDateFormat("hh:mm aa",Locale.ENGLISH);


        for (WorkingHours w : workingHours) {
            formDTOS.add(new WorkingHoursDTO(day.format(w.getStartDate()),hours.format(w.getStartDate()),hours.format(w.getEndDate())));

        }
        System.out.println(formDTOS.get(0).getDayName());
        return formDTOS;
    }

    @PostMapping
    public void addWorkingHour(@RequestBody FormDTO workingHour) throws ParseException {

        WorkingHours w = new WorkingHours();


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        java.util.Date start = sdf1.parse(workingHour.getDate() + ' ' + workingHour.getDateStart());
        w.setStartDate(new Timestamp(start.getTime()));
        java.util.Date end = sdf1.parse(workingHour.getDate() + ' ' + workingHour.getDateStop());
        w.setEndDate(new Timestamp(end.getTime()));

        workingHoursManager.add(w);
    }

    @PutMapping
    public void updateWorkingHour(@RequestBody FormDTO workingHour) throws ParseException {

        WorkingHours w = new WorkingHours();

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        java.util.Date start = sdf1.parse(workingHour.getDate() + ' ' + workingHour.getDateStart());
        w.setStartDate(new Timestamp(start.getTime()));
        java.util.Date end = sdf1.parse(workingHour.getDate() + ' ' + workingHour.getDateStop());
        w.setEndDate(new Timestamp(end.getTime()));

        workingHoursManager.updateWorkingHours(w);
    }

}
