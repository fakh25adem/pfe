package com.example.Client.Service;
import com.example.Client.Model.Alarme;
import com.example.Client.Repository.AlarmeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceAlarmeImpl implements ServiceAlarme{

    private AlarmeRepository alarmeRepository;
    @Override
    public Alarme  insertalarme( LocalDateTime date, String status) {

        Alarme alarme =new Alarme();
        alarme.setStatus_alarm(status);
        alarme.setDatealarm(LocalDateTime.now());
        alarmeRepository.save(alarme);

      return alarme;

    }

    @Override
    public List<Alarme> selectalarme(LocalDateTime date) {
       Sort sort = Sort.by("datealarm").descending();
        List<Alarme> entities = alarmeRepository.findAlarme(date,sort);
        return entities;
    }

    @Override
    public List<Alarme> recherche(LocalDateTime datedebut, LocalDateTime dfin) {

        return alarmeRepository.findByDatealarm(datedebut,dfin);

    }


}
