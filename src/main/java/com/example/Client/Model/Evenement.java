package com.example.Client.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "evenement")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Evenement {
    @Id
    @GeneratedValue

    private Integer id;
    @ManyToOne
    @JoinColumn(name = "personne_id")
    Personne personne;

    @ManyToOne
    @JoinColumn(name = "porte_id")
    Porte porte;
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    private String status_porte;


}
