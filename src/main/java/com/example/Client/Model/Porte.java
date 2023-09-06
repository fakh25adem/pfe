package com.example.Client.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table (name = "Porte")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Porte {
    @Id
    @GeneratedValue
    private Integer idporte;
    @Column(name = "nom")
    private String nomporte;
    @Column(name = "type")
    private String typeporte;

    @OneToOne
    private Waveshare wave;
    @OneToOne
    private Camera camera;
    @OneToMany(mappedBy = "porte")
    private Set<Evenement> evenement;
}
