package com.example.Client.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Waveshare")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Waveshare {
    @Id
    @GeneratedValue
    private Integer id_wav;
    @Column(name = "nomwave")
    private String nomwave;
    @Column(name = "adresseip")
    private String addressIp;
    @Column(name = "status")
    private String statuswav;

    @OneToOne(mappedBy = "wave" ,cascade = CascadeType.ALL)
    @JoinColumn(unique=true,name = "wave_id")
    private Porte porte;


}
