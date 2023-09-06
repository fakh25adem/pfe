package com.example.Client.Controller;

import com.example.Client.Service.ServiceServeur;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/serveur")
public class ServeurController {

  private ServiceServeur serviceServeur;


    @PostMapping("/run")
    public void info(){
       serviceServeur.getInformation();
    }
}
