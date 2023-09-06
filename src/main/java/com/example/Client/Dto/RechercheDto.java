package com.example.Client.Dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class RechercheDto {

    private LocalDateTime debut;
    private LocalDateTime fin;
}
