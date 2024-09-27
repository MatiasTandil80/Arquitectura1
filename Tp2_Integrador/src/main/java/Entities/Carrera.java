package Entities;


import javax.persistence.*;

@Entity
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_carrera")
    private int idCarrera;

    @Column (nullable = false)
    private String nombre;

    public Carrera(String nombre) {
        this.nombre = nombre;
    }

    public Carrera() {
        super();
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public String getNombre() {
        return nombre;
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
