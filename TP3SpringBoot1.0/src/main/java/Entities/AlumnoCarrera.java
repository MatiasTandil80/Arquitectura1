package Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AlumnoCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long idAlumnoCarrera;


    // Consideramos que el sistema es de una universidad con varias facultades y varias carreras
    // por facultad. Un alumno tiene una libreta por facultad
    @Column(name = "nro_libreta")
    private int nroLibreta;

    @ManyToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera", nullable = false)
    private Carrera carrera;

    @Column
    private LocalDate fechaInscripcion;

    @Column
    private LocalDate fechaGraduacion;


    public AlumnoCarrera() {
    }

    public AlumnoCarrera(LocalDate fechaInscripcion, int nroLibreta) {
        this.fechaInscripcion = fechaInscripcion;
        this.fechaGraduacion = null;
        this.nroLibreta = nroLibreta;
    }

    public void setFechaGraduacion(LocalDate fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;

    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Long getIdAlumnoCarrera() {
        return idAlumnoCarrera;
    }

    public void setNroLibreta(int nroLibreta) {
        this.nroLibreta = nroLibreta;
    }

    public int getNroLibreta() {
        return nroLibreta;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

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
                ", antiguedad=" + this.getAntiguedad() +
                ", graduado=" + this.isGraduado() +
                '}';
    }


}
