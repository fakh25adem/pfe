package com.example.Client.Service;
import com.example.Client.Dto.EvenementDto;
import com.example.Client.Model.Alarme;
import com.example.Client.Model.Evenement;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceAlarme {
    Alarme insertalarme( LocalDateTime date, String status);
    List<Alarme> selectalarme(LocalDateTime date);
    List<Alarme> recherche(LocalDateTime datedebut, LocalDateTime dfin );

}
