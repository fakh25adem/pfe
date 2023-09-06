package com.example.Client.Service;

import com.example.Client.Dto.EvenementDto;
import com.example.Client.Model.*;
import com.example.Client.Repository.AlarmeRepository;
import com.example.Client.Repository.EvenementRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceEvenementImpl implements ServiceEvenement{

    private final ServicePersonne servicePersonne;
    private final ServicePorte servicePorte;
    private final ServiceWaveshare servicewWaveshare;
    private  final EvenementRepository evenementRepository;
    private final AlarmeRepository alarmeRepository;


    @Override
    public void CreerEvenement(String addressip, Integer cin, String status) {
        Waveshare a = servicewWaveshare.findByAddressIp(addressip);
        Integer idporte=a.getPorte().getIdporte();
        Porte porte=servicePorte.findByid(idporte);
        Personne personne = servicePersonne.findBycin(cin);
        Evenement evenement = new Evenement();
        evenement.setDate(LocalDateTime.now());
        evenement.setPorte(porte);
        evenement.setPersonne(personne);
        evenement.setStatus_porte(status);
        evenementRepository.save(evenement);
    }

    @Override
    public List<EvenementDto> recherche(LocalDateTime datedebut, LocalDateTime dfin) {
        List<Evenement> evenementList = evenementRepository.findByDate(datedebut,dfin);

        List<EvenementDto> noms = new ArrayList<>();
        for (Evenement event : evenementList) {

            EvenementDto evenementDto = new EvenementDto(
                    event.getPersonne().getId_personne(),
                    event.getPersonne().getNom(), event.getPersonne().getPrenom(),
                    event.getPersonne().getEmail(), event.getPersonne().getTel(),
                    event.getPorte().getNomporte(), event.getStatus_porte(),event.getDate());
            noms.add(evenementDto);
        }
        return noms;


 /*   @Override
    public List<Evenement> recherche(LocalDateTime datedebut, LocalDateTime datefin, int cin) {
        return evenementRepository.findByDateAndCin(datedebut,datefin,cin);
    }*/
    }

    @Override
    public List<Evenement> find(LocalDateTime date) {
        Sort sort = Sort.by("date").descending();
        List<Evenement> entities = evenementRepository.findByEvent(date,sort);
        return entities;
    }
}
