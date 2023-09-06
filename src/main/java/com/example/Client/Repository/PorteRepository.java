package com.example.Client.Repository;

import com.example.Client.Model.Porte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorteRepository extends JpaRepository<Porte,Integer> {
    Porte findByidporte(Integer id);
    long count();



}
