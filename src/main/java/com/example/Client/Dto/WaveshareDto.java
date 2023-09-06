package com.example.Client.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaveshareDto {
    private Integer id_wav;
    private String nomwave;

    private String addressIp;

    private String statuswav;



}
