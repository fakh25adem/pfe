package com.example.Client.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PPEDto {
    private String nom;
    private String prenom;
    private String nomporte;
    private LocalDateTime debut;
    private LocalDateTime fin;
    private String status;


}
