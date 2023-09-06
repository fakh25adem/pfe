package com.example.Client.Service;

import com.example.Client.Dto.EvenementDto;
import com.example.Client.Dto.PPEDto;
import com.example.Client.Model.Evenement;
import jdk.jshell.Snippet;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceEvenement {
    void CreerEvenement(String addressip, Integer cin , String status);
   /* List<Evenement> recherche(LocalDateTime datedebut,LocalDateTime datefin,int cin);*/
 List<EvenementDto> recherche(LocalDateTime datedebut, LocalDateTime dfin );
 List<Evenement> find(LocalDateTime date);


}
