package org.example.nuevo4.DTO.Carrera;


import org.example.nuevo4.Entities.Carrera;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CarreraResponseDTO {

    private final Long id;
    private final String nombre;


    public CarreraResponseDTO(Carrera c ) {
        this.id =c.getIdCarrera();
        this.nombre = c.getNombre();
    }

}
