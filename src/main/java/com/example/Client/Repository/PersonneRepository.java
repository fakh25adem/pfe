package com.example.Client.Repository;

import com.example.Client.Dto.SignUp;
import com.example.Client.Model.Personne;
import com.example.Client.Model.Waveshare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonneRepository extends JpaRepository<Personne,Integer> {
    @Query("SELECT p.cin FROM Personne p WHERE p.role.id = 3")
    List<Integer> getCinByRole();
Personne findBycin(Integer cin );
Personne findByEmail(String nom);
long count();
}
