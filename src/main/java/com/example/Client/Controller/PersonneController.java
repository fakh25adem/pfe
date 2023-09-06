package com.example.Client.Controller;

import com.example.Client.Dto.SignUp;
import com.example.Client.Model.Personne;
import com.example.Client.Model.Role;

import com.example.Client.Repository.PersonneRepository;
import com.example.Client.Service.ServicePersonne;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/personne")
public class PersonneController {
    private PersonneRepository personneRepository;
    @GetMapping("/find")
    public int cinbyrole() {
        List<Integer> integersOut= new ArrayList<>();
        List<Integer> integersIn = personneRepository.getCinByRole();
        Random random = new Random();
        int randomIndex = random.nextInt(integersIn.size());
        int randomValue = integersIn.get(randomIndex);
        integersOut.add(randomValue);
        integersIn.remove(Integer.valueOf(randomValue));
        System.out.println("Valeur aléatoire : " + randomValue);
        System.out.println("valeur supprimer"+integersOut);
        return randomValue;
    }
}








