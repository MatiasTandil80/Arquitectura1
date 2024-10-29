package org.example.nuevo4.DTO;

public class Ej2f_ResponseDTO {

    private Long idCarrera;
    private String nombre;
    private Long cantInscriptos;

    public Ej2f_ResponseDTO() {
    }

    public Ej2f_ResponseDTO(Long idCarrera, String nombre, Long cantInscriptos) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.cantInscriptos = cantInscriptos;
    }

    public Long getIdCarrera() {
        return idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getCantInscriptos() {
        return cantInscriptos;
    }

    @Override
    public String toString() {
        return "Ej2f_ResponseDTO{" +
                "idCarrera=" + idCarrera +
                ", nombre='" + nombre + '\'' +
                ", cantidadInscriptos=" + cantInscriptos +
                '}';
    }
}
