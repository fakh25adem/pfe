package com.example.Client.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EvenementDto {
    private Integer idp;
    private String nom;
    private String prenom ;
    private String email;
    private String tel ;
    private String nomporte;
    private String status;
    private LocalDateTime date;



}
