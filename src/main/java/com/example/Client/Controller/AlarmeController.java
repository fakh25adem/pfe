package com.example.Client.Controller;
import com.example.Client.Dto.Deta;
import com.example.Client.Model.Alarme;
import com.example.Client.Service.ServiceAlarme;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/alarme")

public class AlarmeController {

    private final ServiceAlarme serviceAlarme;

    @Autowired
    public AlarmeController(ServiceAlarme serviceAlarme) {
        this.serviceAlarme = serviceAlarme;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get")
    public List<Alarme> select(@RequestParam("date")  String dateString) {
        LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        return serviceAlarme.selectalarme(date);
    }
  /*  @PostMapping("/send")
public void send(){
        RestTemplate restTemplate = new RestTemplate();

       List<Alarme> alarmes = serviceAlarme.selectalarme();

// Envoyer la liste à l'API de destination
        restTemplate.postForObject("http://localhost:4000/personne/rev", alarmes, Void.class);
    */
  @PostMapping("/recherche")
  public ResponseEntity<?> recherche(@RequestBody Deta deta) {
      System.out.println(deta.getDatedebut());
      System.out.println(deta.getDatefin());
      if(deta.getDatedebut()==null||deta.getDatefin()==null){
          return ResponseEntity.badRequest().body(("Sélectionner deux date"));
      }
      if(deta.getDatedebut().isAfter(deta.getDatefin())){
          return ResponseEntity.badRequest().body("La date du debut est supérieur à la date du fin ");
      }
      if(serviceAlarme.recherche(deta.getDatedebut(), deta.getDatefin()).isEmpty()){
          return ResponseEntity.badRequest().body(" Pas d'alert dans cet date ");
      }

      return ResponseEntity.ok(serviceAlarme.recherche(deta.getDatedebut(), deta.getDatefin()));
  }

}




