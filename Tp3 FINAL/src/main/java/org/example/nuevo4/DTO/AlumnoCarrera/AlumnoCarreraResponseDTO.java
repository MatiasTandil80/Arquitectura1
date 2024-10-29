package org.example.nuevo4.DTO.AlumnoCarrera;

import org.example.nuevo4.Entities.Alumno;
import org.example.nuevo4.Entities.AlumnoCarrera;
import org.example.nuevo4.Entities.Carrera;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AlumnoCarreraResponseDTO {

    private Long nroLibreta;
    private Alumno alumno;
    private Carrera carrera;

    public AlumnoCarreraResponseDTO(Long nroLibreta, Alumno alumno, Carrera carrera) {
        this.nroLibreta = nroLibreta;
        this.alumno = alumno;
        this.carrera = carrera;
    }
    public AlumnoCarreraResponseDTO(AlumnoCarrera alumnoCarrera) {
        this.nroLibreta = alumnoCarrera.getNroLibreta();
        this.alumno = alumnoCarrera.getAlumno();
        this.carrera = alumnoCarrera.getCarrera();
    }

    @Override
    public String toString() {
        return "AlumnoCarreraResponseDTO{" +
                "nroLibreta=" + nroLibreta +
                ", alumno=" + alumno +
                ", carrera=" + carrera +
                '}';
    }
}
