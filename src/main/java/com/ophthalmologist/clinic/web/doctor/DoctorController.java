package com.ophthalmologist.clinic.web.doctor;


import com.ophthalmologist.clinic.models.Account;
import com.ophthalmologist.clinic.models.AccountRequest;
import com.ophthalmologist.clinic.models.Doctor;
import com.ophthalmologist.clinic.models.WorkingHours;
import com.ophthalmologist.clinic.services.AccountManager;
import com.ophthalmologist.clinic.services.DoctorManager;
import com.ophthalmologist.clinic.services.WorkingHoursManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {
    private final DoctorManager doctorManager;
    private final AccountManager accountManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DoctorController(DoctorManager doctorManager, AccountManager accountManager, PasswordEncoder passwordEncoder) {
        this.doctorManager = doctorManager;
        this.accountManager = accountManager;
        this.passwordEncoder = passwordEncoder;
    }

    @CrossOrigin(origins = "http://localhost:4400")
    @GetMapping
    public List<DoctorDTO> getDoctors() {

        List<Doctor> doctors  = doctorManager.getAll();
        List<DoctorDTO> doctorDTOS=  new ArrayList<>();

        for (int i=0; i<doctors.size(); i++ ){
            Integer doctorId = doctors.get(i).getDoctorId();
            doctorDTOS.add(new DoctorDTO());
            doctorDTOS.get(i).setDoctorId(doctorId);
            System.out.println(doctorId);
            doctorDTOS.get(i).setFirstName(doctorManager.getDoctorName());
        }



        return doctorDTOS;

    }


    /*
    @GetMapping(value = "/workingHours/{doctorId}")
    public List<WorkingHours> getWorkingHours(@PathVariable Integer doctorId){
        return workingHoursManager.getWorkingHours(doctorId);
    }*/
    @CrossOrigin(origins = "http://localhost:4400")
    @PostMapping
    public Doctor addDoctor(@RequestBody DoctorDTO doctorDTO) throws AccountNotFoundException {

        Account user = new AccountRequest(doctorDTO.getUsername(), passwordEncoder.encode(doctorDTO.getPasswordHash()))
                .setFirst_name(doctorDTO.getFirstName())
                .setLast_name(doctorDTO.getLastName())
                .setRole("doctor")
                .createUser();


        accountManager.save(user);

        Account savedDoctor = accountManager.getAccountByUsernameOrThrow(doctorDTO.getUsername());

        Doctor doctor = new Doctor();
        doctor.setPhone(doctorDTO.getPhone());
        doctor.setAccountId(savedDoctor.getAccountId());
        return doctorManager.save(doctor);
    }


}
