package com.example.springboot.dtos.input;

import lombok.Data;

@Data
public class TurnoInputDTO {
    private Long id;
    MascotaInputDTO mascota;
}
