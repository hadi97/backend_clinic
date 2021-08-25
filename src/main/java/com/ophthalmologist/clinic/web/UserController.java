package com.ophthalmologist.clinic.web;

import com.ophthalmologist.clinic.models.Account;
import com.ophthalmologist.clinic.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserManager userManager;
    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @CrossOrigin
    @GetMapping(value = "/all")
    public List<Account> getAll(){
        return userManager.getAll();
    }

    @CrossOrigin
    @GetMapping(value = "/userDoctors")
    public List<Account> getDoctors(){
        return userManager.getDoctors();
    }

    @CrossOrigin
    @GetMapping(value = "/userPatients")
    public List<Account> getPatients(){
        return userManager.getPatients();
    }

    @CrossOrigin
    @GetMapping("/getName")
    public Account getName(@RequestParam String username) {
        return userManager.getUserName(username);
    }



    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Integer userId){
        userManager.delete(userId);
    }

}
