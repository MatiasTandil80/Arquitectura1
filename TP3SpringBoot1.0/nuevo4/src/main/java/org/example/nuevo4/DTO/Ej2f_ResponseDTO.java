package org.example.nuevo4.DTO;

public class Ej2f_ResponseDTO {

    private Long idCarrera;
    private String nombre;
    private int cantidadInscriptos;

    public Ej2f_ResponseDTO(Long idCarrera, String nombre, int cantidadInscriptos) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.cantidadInscriptos = cantidadInscriptos;
    }

    @Override
    public String toString() {
        return "Ej2f_ResponseDTO{" +
                "idCarrera=" + idCarrera +
                ", nombre='" + nombre + '\'' +
                ", cantidadInscriptos=" + cantidadInscriptos +
                '}';
    }
}
