package Entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Alumno {

    @Id
    private Long dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column
    private LocalDate fechaNacimiento;

    @Column
    private String genero;

    @Column
    private String ciudad;


    public Alumno() {
        super();
    }

    public Alumno(Long dni, String nombre, String apellido, LocalDate fechaNacimiento, String genero, String ciudad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.ciudad = ciudad;

    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Long getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return (LocalDate.now().getYear() - fechaNacimiento.getYear());
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    @Override
    public String toString() {
        return "Alumno{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + this.getEdad() +
                ", genero='" + genero + '\'' +
                ", ciudad='" + ciudad + '\'' +
               '}';
    }
}
