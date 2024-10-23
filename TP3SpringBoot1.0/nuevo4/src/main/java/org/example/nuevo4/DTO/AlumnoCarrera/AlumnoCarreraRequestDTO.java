package org.example.nuevo4.DTO.AlumnoCarrera;

import org.example.nuevo4.Entities.Alumno;
import org.example.nuevo4.Entities.Carrera;

import java.time.LocalDate;

public class AlumnoCarreraRequestDTO {

    private Long nroLibreta;
    private Alumno alumno;
    private Carrera carrera;
    private LocalDate fechaInscripcion;
    private Long idAlumnoCarrera;

    public AlumnoCarreraRequestDTO(Long idAlumnoCarrera, Long nroLibreta, Alumno alumno, Carrera carrera, LocalDate fechaInscripcion) {
        this.nroLibreta = nroLibreta;
        this.alumno = alumno;
        this.carrera = carrera;
        this.fechaInscripcion = fechaInscripcion;
        this.idAlumnoCarrera = idAlumnoCarrera;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public Long getNroLibreta() {
        return nroLibreta;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public Long getIdAlumnoCarrera() {
        return idAlumnoCarrera;
    }
}
