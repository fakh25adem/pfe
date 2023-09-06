package com.example.Client.Controller;

import com.example.Client.Dto.Deta;
import com.example.Client.Dto.EvenementDto;
import com.example.Client.Dto.RechercheDto;
import com.example.Client.Dto.WaveshareDto;
import com.example.Client.Model.Evenement;
import com.example.Client.Model.Waveshare;
import com.example.Client.Service.ServiceEvenement;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/event")
public class EvenementController {
    private ServiceEvenement serviceEvenement;

    @PostMapping("/recherche")
    public ResponseEntity<?> recherche(@RequestBody  Deta deta) {
        System.out.println(deta.getDatedebut());
        System.out.println(deta.getDatefin());
        if(deta.getDatedebut()==null||deta.getDatefin()==null){
            return ResponseEntity.badRequest().body(("Sélectionner deux date"));
        }
        if(deta.getDatedebut().isAfter(deta.getDatefin())){
            return ResponseEntity.badRequest().body("La date du debut est supérieur à la date du fin ");
        }
        if (serviceEvenement.recherche(deta.getDatedebut(), deta.getDatefin()).isEmpty()) {
            return ResponseEntity.badRequest().body((" Pas d'événements dans cet date "));
        }
        return ResponseEntity.ok(serviceEvenement.recherche(deta.getDatedebut(), deta.getDatefin()));
    }

    @GetMapping("/get")
    public List<EvenementDto> find(@RequestParam("date")  String dateString ){
        LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        List<Evenement> eventList= serviceEvenement.find(date);
        // supposons que vous avez déjà une liste d'objets Alarme

        List<EvenementDto> listeevent = new ArrayList<>();

        for (Evenement event : eventList) {
            // supposons que le nom de l'alarme se trouve dans une propriété "nom" de l'objet Alarme
            EvenementDto evenementDto =new EvenementDto(event.getPersonne().getId_personne() ,event.getPersonne().getNom(),
            event.getPersonne().getPrenom(),event.getPersonne().getEmail(),event.getPersonne().getTel(),
                    event.getPorte().getNomporte(),event.getStatus_porte(),event.getDate());
          listeevent.add(evenementDto);
        }
        return listeevent;
    }
}


