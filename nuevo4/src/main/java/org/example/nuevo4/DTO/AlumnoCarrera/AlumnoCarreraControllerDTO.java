package org.example.nuevo4.DTO.AlumnoCarrera;

import org.example.nuevo4.Entities.Alumno;
import org.example.nuevo4.Entities.Carrera;

import java.time.LocalDate;

public class AlumnoCarreraControllerDTO {

    private Long nroLibreta;
    private Long dni;
    private Long idCarrera;
    private LocalDate fechaInscripcion;

    public AlumnoCarreraControllerDTO(Long nroLibreta, Long dni, Long idCarrera, LocalDate fechaInscripcion) {
        this.nroLibreta = nroLibreta;
        this.dni = dni;
        this.idCarrera = idCarrera;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Long getNroLibreta() {
        return nroLibreta;
    }

    public void setNroLibreta(Long nroLibreta) {
        this.nroLibreta = nroLibreta;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Long getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Long idCarrera) {
        this.idCarrera = idCarrera;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
