package org.example.nuevo4.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
public class AlumnoCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlumnoCarrera;

    // Consideramos que el sistema es de una universidad con varias facultades y varias carreras
    // por facultad. Un alumno tiene una libreta por facultad
    @Column(name = "nro_libreta")
    private Long nroLibreta;

    @ManyToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera", nullable = false)
    private Carrera carrera;

    @Column (name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;

    @Column (name = "fecha_graduacion")
    private LocalDate fechaGraduacion;


    public AlumnoCarrera() {

    }

    public AlumnoCarrera(LocalDate fechaInscripcion, Long nroLibreta) {

        this.fechaInscripcion = fechaInscripcion;
        this.nroLibreta = nroLibreta;
    }

    public AlumnoCarrera(Long nroLibreta, Alumno alumno, Carrera carrera, LocalDate fechaInscripcion ) {

        try {
            this.nroLibreta = nroLibreta;
            this.alumno = alumno;
            this.carrera = carrera;
            this.fechaInscripcion = fechaInscripcion;

        } catch (EntityNotFoundException e) {
            // Manejo de la excepción si no se encuentra la entidad
            System.err.println("No se encontró la entidad: " + e.getMessage());

        } catch (Exception e) {
            // Manejo de cualquier otra excepción
            System.err.println("Ocurrió un error: " + e.getMessage());
        }

    }

    public AlumnoCarrera(AlumnoCarrera alumnoCarrera) {

        this.idAlumnoCarrera = alumnoCarrera.getIdAlumnoCarrera();
        this.nroLibreta = alumnoCarrera.getNroLibreta();
        this.alumno = alumnoCarrera.getAlumno();
        this.carrera = alumnoCarrera.getCarrera();
        this.fechaInscripcion = alumnoCarrera.getFechaInscripcion();
        this.fechaGraduacion = alumnoCarrera.getFechaGraduacion();
        this.nroLibreta = alumnoCarrera.getNroLibreta();
    }

    public void setFechaGraduacion(LocalDate fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;

    }

    public void setIdAlumnoCarrera(Long idAlumnoCarrera) { this.idAlumnoCarrera = idAlumnoCarrera; }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Long getIdAlumnoCarrera() {
        return idAlumnoCarrera;
    }

    public void setNroLibreta(Long nroLibreta) {
        this.nroLibreta = nroLibreta;
    }

    public Long getNroLibreta() {
        return nroLibreta;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }

    public LocalDate getFechaGraduacion() { return fechaGraduacion; }

    public Alumno getAlumno() {
        return alumno;
    }

    public Carrera getCarrera() {
        return carrera;
    }


    public int getAntiguedad() {//Retorna la antiguedad en años, si no está graduado. Si está graduado  retorn cero.
        if (!this.isGraduado()) {
            return (LocalDate.now().getYear() - this.fechaInscripcion.getYear());
        }
        return 0;
    }


    public boolean isGraduado() {
        return (this.fechaGraduacion != null);
    }


    @Override
    public String toString() {
        return "AlumnoCarrera{" +
                "idAlumnoCarrera=" + idAlumnoCarrera +
                "nroLibreta=" + nroLibreta +
                ", alumno=" + alumno +
                ", carrera=" + carrera +
                ", fechaInscripcion=" + fechaInscripcion +
                ", antiguedad=" + this.getAntiguedad() +
                ", fechaGraduacion=" + this.getFechaGraduacion() +
                ", graduado=" + this.isGraduado() +
                '}';
    }
}
