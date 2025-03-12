package com.example.springboot.models.entities;
import com.example.springboot.dtos.input.MascotaInputDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Mascota {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String raza;

    public Mascota(MascotaInputDTO mascota) {
        this.id = mascota.getId();
        this.nombre = mascota.getNombre();
        this.raza = mascota.getRaza();
    }


}