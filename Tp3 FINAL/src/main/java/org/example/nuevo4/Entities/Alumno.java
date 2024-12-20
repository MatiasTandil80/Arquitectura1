package org.example.nuevo4.Entities;

import org.example.nuevo4.DTO.Alumno.AlumnoRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
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


    public Alumno(Long dni, String nombre, String apellido, LocalDate fechaNacimiento, String genero, String ciudad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.ciudad = ciudad;

    }

    public Alumno(AlumnoRequestDTO alumnoRequestDTO) {
        this.dni = alumnoRequestDTO.getDni();
        this.nombre = alumnoRequestDTO.getNombre();
        this.apellido = alumnoRequestDTO.getApellido();
    }

    public Alumno(Alumno alumno) {
        this.dni = alumno.dni;
        this.nombre = alumno.getNombre();
        this.apellido = alumno.getApellido();
        this.fechaNacimiento = alumno.getFechaNacimiento();
        this.genero = alumno.getGenero();
        this.ciudad = alumno.getCiudad();
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
