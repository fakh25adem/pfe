package com.example.Client.Repository;

import com.example.Client.Model.Evenement;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface EvenementRepository extends JpaRepository<Evenement,Integer> {
   // long countBydate(LocalDateTime startDate, LocalDateTime endDate);
    @Query(" Select Count(e) FROM Evenement e Where e.date BETWEEN :Debut AND :Fin")
    int countByDate(@Param("Debut") LocalDateTime dateDebut,
                    @Param("Fin") LocalDateTime dateFin);

    @Query(" Select e FROM Evenement e Where e.date BETWEEN :dateDebut AND :dateFin")
    List<Evenement> findByDate(@Param("dateDebut") LocalDateTime dateDebut,
                               @Param("dateFin") LocalDateTime dateFin);
    @Query("  SELECT e FROM Evenement e WHERE e.date > :date")
    List<Evenement> findByEvent(@Param("date") LocalDateTime date, Sort sort);


}