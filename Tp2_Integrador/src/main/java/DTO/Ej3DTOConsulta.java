package DTO;

//Esta clase corresponde al DTO contenido en cada elemento de las listas individuales
// de Inscriptos o Graduados del Ejercicio 3)
public class Ej3DTOConsulta {

    private String nombreCarrera;
    private int anio;
    private Long cantidad;

    public Ej3DTOConsulta(String nombreCarrera, int anio, Long cantidad) {
        this.nombreCarrera = nombreCarrera;
        this.anio = anio;
        this.cantidad = cantidad;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public int getAnio() {
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
