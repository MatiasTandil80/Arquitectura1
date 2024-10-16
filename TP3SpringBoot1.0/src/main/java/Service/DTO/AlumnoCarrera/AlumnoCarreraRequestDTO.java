package Service.DTO.AlumnoCarrera;

import Entities.Alumno;
import Entities.Carrera;

import java.time.LocalDate;

public class AlumnoCarreraRequestDTO {

    private int nroLibreta;
    private Alumno alumno;
    private Carrera carrera;
    private LocalDate fechaInscripcion;
    private Long idAlumnoCarrera;

    public AlumnoCarreraRequestDTO(Long idAlumnoCarrera, int nroLibreta, Alumno alumno, Carrera carrera, LocalDate fechaInscripcion) {
        this.nroLibreta = nroLibreta;
        this.alumno = alumno;
        this.carrera = carrera;
        this.fechaInscripcion = fechaInscripcion;
        this.idAlumnoCarrera = idAlumnoCarrera;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public int getNroLibreta() {
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
