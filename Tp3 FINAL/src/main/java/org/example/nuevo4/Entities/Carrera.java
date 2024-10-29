package org.example.nuevo4.Entities;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private Long idCarrera;

    @Column (nullable = false)
    private String nombre;

    public Carrera(String nombre) {
        this.nombre = nombre;
    }

    public Carrera() {
        super();
    }

    public Long getIdCarrera() {
        return this.idCarrera;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "idCarrera=" + idCarrera +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
