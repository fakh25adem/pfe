package com.example.Client.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.Set;

@Entity
@Table (name = "Personne")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Personne {
    @Id
    @GeneratedValue
    private Integer id_personne;

    @Column(name = "cin")
    private Integer cin;


    @Column(name = "nom")
    private String nom;


    @Column(name = "prenom")
    private String prenom;

    @Email
    @Column(name = "email")
    private String email;


    @Column(name = "mot_de_passe ")
    private String mdp;

    @Column(name = "tel")
    private String tel;

    @OneToMany(mappedBy = "personne")
    private Set<Evenement> evenement;

    @OneToOne
    private Role role;

    public Personne(String nom) {
        this.nom = nom;


    }
}
