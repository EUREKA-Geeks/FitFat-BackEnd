package com.example.FitFat.Controller;

import com.example.FitFat.Models.Trainee;
import com.example.FitFat.Models.Trainer;
import com.example.FitFat.Models.Users;
import com.example.FitFat.Repositories.TraineeRepository;
import com.example.FitFat.Repositories.TrainerRepository;
import com.example.FitFat.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    TraineeRepository traineeRepository;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/users/{email}")
    public Users getUser(@PathVariable String email) {
        return usersRepository.findByEmail(email);
    }


    //User can edit his profile
    @PutMapping("/trainee/{email}")
    public ResponseEntity updateFromTraineeToTrainer(@PathVariable String email) {
        try {
            Trainee trainee = traineeRepository.findByEmail(email);
            System.out.println(trainee);
            Trainer trainer = new Trainer();
            trainer.setEmail(trainee.getEmail());
            trainer.setName(trainee.getName());
//            System.out.println(trainee.getEmail());
            traineeRepository.delete(trainee);
//            System.out.println(trainee.getEmail());
            trainerRepository.save(trainer);
            return ResponseEntity.ok(trainee);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("no");
        }
    }

    @PostMapping("/create")
    public String createUser(@RequestBody Trainee trainee) {
        traineeRepository.save(trainee);
        System.out.println(trainee);
        return "yes";

    }


}
