package com.example.Client.Controller;


import com.example.Client.Dto.PorteDto;

import com.example.Client.Model.Evenement;

import com.example.Client.Service.ServiceEvenement;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/porte")
public class PorteController {
       private ServiceEvenement serviceEvenement;

        @GetMapping ("/get")
        public List<PorteDto> getporte(@RequestParam("datenow")  String dateString){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);


            List<Evenement> eventList= serviceEvenement.find(dateTime);
            // supposons que vous avez déjà une liste d'objets Alarme

            List<PorteDto> listeporte = new ArrayList<>();

            for (Evenement event : eventList) {
                // supposons que le nom de l'alarme se trouve dans une propriété "nom" de l'objet Alarme
                PorteDto porteDto =new PorteDto(event.getPorte().getIdporte() , event.getPorte().getNomporte(),
                       event.getStatus_porte(), event.getDate());
                listeporte.add(porteDto);
            }
//            List<Porte>portes= servicePorte.find();
//            // supposons que vous avez déjà une liste d'objets Alarme
//
//            List<PorteDto> listeporte = new ArrayList<>();
//
//            for (Porte porte : portes) {
//                // supposons que le nom de l'alarme se trouve dans une propriété "nom" de l'objet Alarme
//               PorteDto porteDto =new PorteDto(porte.getIdporte(), porte.getNomporte(),
//                        porte.getTypeporte());
//                listeporte.add(porteDto);
//            }

            return listeporte;
        }

    }
