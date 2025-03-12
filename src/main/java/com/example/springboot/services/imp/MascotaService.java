package com.example.springboot.services.imp;

import com.example.springboot.dtos.input.MascotaInputDTO;
import com.example.springboot.dtos.output.MascotaOutputDTO;
import com.example.springboot.exceptions.NotFoundException;
import com.example.springboot.mappers.MascotaMapper;
import com.example.springboot.models.entities.Mascota;
import com.example.springboot.models.repositories.MascotaRepository;
import com.example.springboot.services.IMascotaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MascotaService implements IMascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public List<MascotaOutputDTO> obtenerTodas() {
        return mascotaRepository.findAll().stream()
                .map(MascotaMapper::crearAPartirDe)
                .toList();
    }

    @Override
    public MascotaOutputDTO obtenerPorId(Long id) {
        return mascotaRepository.findById(id)
                .map(MascotaMapper::crearAPartirDe)
                .orElseThrow(() -> new NotFoundException("Mascota no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public Long crearMascota(MascotaInputDTO mascotaInput) {
        Mascota nuevaMascota = new ObjectMapper().convertValue(mascotaInput, Mascota.class);
        Mascota mascotaGuardada = mascotaRepository.save(nuevaMascota);
        return mascotaGuardada.getId();
    }

    @Override
    @Transactional
    public MascotaOutputDTO modificarMascota(MascotaInputDTO mascotaInput, Long id) {
        Mascota mascotaAModificar = new ObjectMapper().convertValue(mascotaInput, Mascota.class);

        if (!mascotaRepository.existsById(id))
            throw new NotFoundException("Mascota no encontrada con id: " + id);

        mascotaAModificar.setId(id);
        Mascota mascotaActualizada = mascotaRepository.save(mascotaAModificar);
        return MascotaMapper.crearAPartirDe(mascotaActualizada);
    }

    @Override
    @Transactional
    public void eliminarMascota(Long id) {
        if (!mascotaRepository.existsById(id))
            throw new NotFoundException("Mascota no encontrada con id: " + id);

        mascotaRepository.deleteById(id);
    }
}
