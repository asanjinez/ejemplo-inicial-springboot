package com.example.springboot.controllers;

import com.example.springboot.dtos.input.MascotaInputDTO;
import com.example.springboot.dtos.output.MascotaOutputDTO;
import com.example.springboot.services.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private IMascotaService mascotaService;

    @GetMapping
    public ResponseEntity<List<MascotaOutputDTO>> obtenerMascotas() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.mascotaService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<String> cargarMascota(@RequestBody MascotaInputDTO mascota) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.mascotaService.crearMascota(mascota).toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaOutputDTO> obtenerMascotaPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.mascotaService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        this.mascotaService.eliminarMascota(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaOutputDTO> modificarMascota(@RequestBody MascotaInputDTO mascotaNuevo, @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.mascotaService.modificarMascota(mascotaNuevo, id));
    }
}
