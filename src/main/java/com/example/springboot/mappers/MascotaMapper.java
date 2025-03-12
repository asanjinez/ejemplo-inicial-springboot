package com.example.springboot.mappers;

import com.example.springboot.dtos.input.MascotaInputDTO;
import com.example.springboot.dtos.output.MascotaOutputDTO;
import com.example.springboot.models.entities.Mascota;

public class MascotaMapper {

    public static MascotaOutputDTO crearAPartirDe(Mascota mascota) {
        return MascotaOutputDTO
                .builder()
                .id(mascota.getId())
                .nombre(mascota.getNombre())
                .raza(mascota.getRaza())
                .build();
    }

    public static Mascota crearAPartirDe(MascotaInputDTO mascotaInputDTO) {
        Mascota mascota = new Mascota();
        mascota.setId(mascotaInputDTO.getId());
        mascota.setNombre(mascotaInputDTO.getNombre());
        mascota.setRaza(mascotaInputDTO.getRaza());
        return mascota;
    }

}