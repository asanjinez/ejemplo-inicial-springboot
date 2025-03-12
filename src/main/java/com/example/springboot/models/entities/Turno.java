package com.example.springboot.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Turno {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="mascota_id",referencedColumnName="id")
    private Mascota mascota;

}