package com.example.springboot.services.imp;

import com.example.springboot.dtos.input.TurnoInputDTO;
import com.example.springboot.dtos.output.TurnoOutputDTO;
import com.example.springboot.exceptions.NotFoundException;
import com.example.springboot.mappers.TurnoMapper;
import com.example.springboot.models.entities.Turno;
import com.example.springboot.models.repositories.MascotaRepository;
import com.example.springboot.models.repositories.TurnoRepository;
import com.example.springboot.services.ITurnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TurnosService implements ITurnosService {
    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    //GET
    @Override
    public List<TurnoOutputDTO> obtenerTodos() {
        return turnoRepository.findAll().stream()
                .map(TurnoMapper::crearAPartirDe)
                .toList();
    }

    @Override
    public TurnoOutputDTO obtenerPorId(Long id) {
        return turnoRepository.findById(id)
                .map(TurnoMapper::crearAPartirDe)
                .orElseThrow(() -> new NotFoundException("Turno no encontrado con id: " + id));
    }

    //CREATE (POST)
    @Override
    @Transactional
    public Long crearTurno(TurnoInputDTO turnoInput) {
        Long mascotaId = turnoInput.getMascota().getId();
        if(!mascotaRepository.existsById(mascotaId))
            throw new NotFoundException("Mascota no encontrada con id: " + mascotaId);

        Turno nuevoTurno = TurnoMapper.crearAPartirDe(turnoInput);
        Turno turnoGuardado = turnoRepository.save(nuevoTurno);
        return turnoGuardado.getId();
    }

    //UPDATE (PUT)
    @Override
    @Transactional
    public TurnoOutputDTO modificarTurno(TurnoInputDTO turnoInput, Long id) {
        Turno turnoAModificar = TurnoMapper.crearAPartirDe(turnoInput);

        if (!turnoRepository.existsById(id))
            throw new NotFoundException("Turno no encontrado con id: " + id);

        turnoAModificar.setId(id);
        Turno turnoActualizado = turnoRepository.save(turnoAModificar);
        return TurnoMapper.crearAPartirDe(turnoActualizado);
    }

    @Override
    @Transactional
    public void eliminarTurno(Long id) {
        if (!turnoRepository.existsById(id))
            throw new NotFoundException("Turno no encontrado con id: " + id);

        turnoRepository.deleteById(id);
    }
}