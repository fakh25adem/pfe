package com.example.Client.Repository;

import com.example.Client.Model.Alarme;
import com.example.Client.Model.Evenement;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface AlarmeRepository extends JpaRepository<Alarme,Integer> {
    long count();
    @Query(" Select Count(a) FROM Alarme a Where a.datealarm BETWEEN :Debut AND :Fin")
    int countByDatealarm(@Param("Debut") LocalDateTime dateDebut,
                    @Param("Fin") LocalDateTime dateFin);
    @Query("SELECT Count(a), DAYOFWEEK(a.datealarm) FROM Alarme a WHERE WEEK(a.datealarm) = :weekNumber GROUP BY DAYOFWEEK(a.datealarm)")
    List<int[]> findByWeek(@Param("weekNumber") int week);
    @Query(" Select a FROM Alarme a Where a.datealarm BETWEEN :dateDebut AND :dateFin")
    List<Alarme> findByDatealarm(@Param("dateDebut") LocalDateTime dateDebut,
                               @Param("dateFin") LocalDateTime dateFin);
    @Query("  SELECT a FROM Alarme a WHERE a.datealarm > :date")
    List<Alarme> findAlarme(@Param("date") LocalDateTime date, Sort sort);
}
