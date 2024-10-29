package org.example.nuevo4.DTO.Alumno;

import org.example.nuevo4.Entities.Alumno;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AlumnoResponseDTO {

    private final Long dni;
    private final String nombre;
    private final String apellido;

    public AlumnoResponseDTO() {
        this.dni = null;
        this.nombre = null;
        this.apellido = null;
    }

    public AlumnoResponseDTO(Alumno a ) {
        this.dni = a.getDni();
        this.nombre = a.getNombre();
        this.apellido = a.getApellido();
    }

}
