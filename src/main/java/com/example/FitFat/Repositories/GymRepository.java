package com.example.FitFat.Repositories;

import com.example.FitFat.Models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {


    Gym findGymById(Long id);

    Gym findGymByName(String username);
}