package com.example.Client.Service;
import com.example.Client.Dto.SignUp;
import com.example.Client.Model.Personne;
import org.springframework.http.ResponseEntity;



public interface ServicePersonne {
    ResponseEntity<Object> creer(Personne personne);
    Personne findBycin(Integer cin );
    Personne ajouter(SignUp signUp);



}
