package com.example.springboot.mappers;

import com.example.springboot.dtos.input.TurnoInputDTO;
import com.example.springboot.dtos.output.TurnoOutputDTO;
import com.example.springboot.models.entities.Turno;

public class TurnoMapper {

    public static TurnoOutputDTO crearAPartirDe(Turno turno) {
        return TurnoOutputDTO
                .builder()
                .id(turno.getId())
                .mascota(MascotaMapper.crearAPartirDe(turno.getMascota()))
                .build();
    }

    public static Turno crearAPartirDe(TurnoInputDTO turnoInput) {
        Turno turno = new Turno();
        turno.setMascota(MascotaMapper.crearAPartirDe(turnoInput.getMascota()));

        return turno;
    }

}
