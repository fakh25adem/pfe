package com.example.Client.Service;

import com.example.Client.Dto.ClassDto;
import com.example.Client.Dto.DashDto;
import com.example.Client.Model.Personne;
import com.example.Client.Repository.AlarmeRepository;
import com.example.Client.Repository.EvenementRepository;
import com.example.Client.Repository.PersonneRepository;
import com.example.Client.Repository.PorteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class ServiceDashImpl implements  ServiceDash{
    private PersonneRepository personneRepository;
    private EvenementRepository evenementRepository;
    private AlarmeRepository alarmeRepository;
    private PorteRepository porteRepository;

    @Override
    public DashDto getdash() {

        LocalDateTime n = LocalDateTime.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();

        LocalDate day = LocalDate.now();
        LocalDateTime endOfDay = day.atTime(LocalTime.MAX);


        DashDto dashDto=new DashDto(
                (int) personneRepository.count(), (int) porteRepository.count(),
                evenementRepository.countByDate(startOfDay,endOfDay),
            5);

        return dashDto;
    }


}
