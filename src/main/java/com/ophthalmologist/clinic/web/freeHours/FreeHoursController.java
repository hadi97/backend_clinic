package com.ophthalmologist.clinic.web.freeHours;

import com.ophthalmologist.clinic.web.visits.Period;
import com.ophthalmologist.clinic.services.VisitManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/freeHours")
@RestController
public class FreeHoursController {
    private final VisitManager manager;

    @Autowired

    public FreeHoursController(VisitManager manager) {
        this.manager = manager;
    }
    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping
    public List <VisitInfo> getAll(){
        return manager.availableVisits(30);

    }



    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping("/getDay")
    public List<String> getForDay(@RequestParam String date) throws ParseException {
        System.out.println("date :"+ date);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MM-yyyy");
        List<String> strings = new ArrayList<>();
        List<Period> periods =  manager.preparePeriodsForDoctor( sdf.parse(date));
        for (Period period : periods){
            strings.add(period.getHourName());
        }
    return strings;
    }


    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping("/getDays")
    public List<FreeDTO> getDays(){
        List<FreeDTO> freeDTOS= new ArrayList<>();
        for(String d : manager.getDays()) {
            freeDTOS.add(new FreeDTO(d));
        }
        return freeDTOS;
    }

    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping("/getAvailable")
    public List<AvailableDTO> getDays(@RequestParam String day) throws ParseException {
        List<AvailableDTO> availableDTOS= new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MM-yyyy");

        List<Period> available = manager.preparePeriodsForDoctor(sdf.parse(day));
        for(Period period : available) {
            System.out.println(day);
            System.out.println(period.getHourName());
            availableDTOS.add(new AvailableDTO(period.getHourName(),"yes"));
        }

        return availableDTOS;
    }

    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping("/getReserved")
    public List<AvailableDTO> getNoDays(@RequestParam String day) throws ParseException {
        List<AvailableDTO> availableDTOS= new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MM-yyyy");

        List<Period> taken = manager.takenVisits(sdf.parse(day));
        for (Period period : taken) {
            availableDTOS.add(new AvailableDTO(period.getHourName(),"no"));
            System.out.println(day+" MAYBE SUCK IT? : "+period.getHourName());
        }
        return availableDTOS;
    }


}
