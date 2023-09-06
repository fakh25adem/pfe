package com.example.Client.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor

public class PorteDto {
    private Integer idporte;

    private String nomporte;

    private String statusporte;
    private LocalDateTime date;

}
