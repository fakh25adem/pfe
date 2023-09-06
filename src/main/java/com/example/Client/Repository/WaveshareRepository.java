package com.example.Client.Repository;

import com.example.Client.Model.Personne;
import com.example.Client.Model.Waveshare;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WaveshareRepository extends JpaRepository<Waveshare,Integer> {
    @Transactional
    @Modifying
    @Query(value = "update waveshare  set status =:status where adresseip =:adresseip",nativeQuery = true)
    void modify(@Param("status")String sa,@Param("adresseip") String s);
   /* @Query("SELECT w FROM Waveshare w INNER JOIN FETCH w.porte p WHERE w.adrip_wav = ?1")
    public Waveshare findid(String adrr);*/
    /*List<Waveshare> find(String adr);*/
   @Query("Select w FROM Waveshare w Where w.statuswav =:status")
   List<Waveshare> deconnecter(@Param("status") String s);
    Waveshare findByAddressIp(String addressIp);
    @Transactional
    void deleteByAddressIp(String adrr);
}


