package org.example.nuevo4.DTO.Carrera;

import org.example.nuevo4.Entities.Carrera;

public class CarreraRequestDTO {

    private final String nombre;

    public CarreraRequestDTO(Carrera c) {

        this.nombre = c.getNombre();
    }

    public String getNombre() {
        return nombre;
    }
}


