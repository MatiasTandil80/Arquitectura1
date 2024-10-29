package org.example.nuevo4.DTO;

//Esta clase corresponde al DTO contenido en cada elemento de la lista Resultado del Ejercicio 3)
public class Ej3DTOResultado {

    private String nombreCarrera;
    private Integer anioInscriptos;
    private Integer anioGraduados;
    private Long cantidadInscriptos;
    private Long cantidadGraduados;

    public Ej3DTOResultado(){
        super();
    }

    public Ej3DTOResultado(String nombreCarrera, Integer anioInscriptos, Integer anioGraduados, Long cantidadInscriptos, Long cantidadGraduados) {
        this.nombreCarrera = nombreCarrera;
        this.anioInscriptos = anioInscriptos;
        this.anioGraduados = anioGraduados;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadGraduados = cantidadGraduados;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public Integer getAnioInscriptos() {
        return anioInscriptos;
    }

    public Integer getAnioGraduados() {
        return anioGraduados;
    }

    public Long getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    public Long getCantidadGraduados() {
        return cantidadGraduados;
    }

    @Override
    public String toString() {
        return "{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", añoInscriptos=" + anioInscriptos +
                ", añoGraduados=" + anioGraduados +
                ", cantidadInscriptos=" + cantidadInscriptos +
                ", cantidadGraduados=" + cantidadGraduados +
                '}';
    }
}
