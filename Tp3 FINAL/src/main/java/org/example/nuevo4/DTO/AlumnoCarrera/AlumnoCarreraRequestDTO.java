package org.example.nuevo4.DTO.AlumnoCarrera;

import java.time.LocalDate;

public class AlumnoCarreraRequestDTO {

    private Long idAlumnoCarrera;
    private Long nroLibreta;
    private Long dni;
    private Long idCarrera;
    private LocalDate fechaInscripcion;
    private LocalDate fechaGraduacion;

    public AlumnoCarreraRequestDTO(Long idAlumnoCarrera, Long nroLibreta, Long dni, Long idCarrera, LocalDate fechaInscripcion, LocalDate fechaGraduacion) {
        this.idAlumnoCarrera = idAlumnoCarrera;
        this.nroLibreta = nroLibreta;
        this.dni = dni;
        this.idCarrera = idCarrera;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaGraduacion = fechaGraduacion;
    }

    public Long getIdAlumnoCarrera() {
        return idAlumnoCarrera;
    }

    public Long getNroLibreta() {
        return nroLibreta;
    }

    public Long getDni() {
        return dni;
    }

    public Long getIdCarrera() {
        return idCarrera;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public LocalDate getFechaGraduacion() {
        return fechaGraduacion;
    }

    @Override
    public String toString() {
        return "AlumnoCarreraRequestDTO{" +
                "idAlumnoCarrera=" + idAlumnoCarrera +
                ", nroLibreta=" + nroLibreta +
                ", dni=" + dni +
                ", idCarrera=" + idCarrera +
                ", fechaInscripcion=" + fechaInscripcion +
                ", fechaGraduacion=" + fechaGraduacion +
                '}';
    }

    public void setFechaGraduacion(LocalDate fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;
    }
}