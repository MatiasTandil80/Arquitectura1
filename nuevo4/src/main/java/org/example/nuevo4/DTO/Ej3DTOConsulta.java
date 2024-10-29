package org.example.nuevo4.DTO;

//Esta clase corresponde al DTO contenido en cada elemento de las listas individuales
// de Inscriptos o Graduados del Ejercicio 3)
public class Ej3DTOConsulta {

    private String nombreCarrera;
    private Integer anio;
    private Long cantidad;

    public Ej3DTOConsulta(String nombreCarrera, Integer anio, Long cantidad) {
        this.nombreCarrera = nombreCarrera;
        this.anio = anio;
        this.cantidad = cantidad;
    }

    public Ej3DTOConsulta() {}

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public Integer getAnio() {
        return anio;
    }

    public Long getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Ej3DTOConsulta{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", anio=" + anio +
                ", cantidad=" + cantidad +
                '}';
    }
}
