package org.example.nuevo4.DTO;

//Esta clase corresponde al DTO contenido en cada elemento de la lista Resultado del Ejercicio 3)
public class Ej3DTOResultado {

    private String nombreCarrera;
    private int añoInscriptos;
    private int añoGraduados;
    private Long cantidadInscriptos;
    private Long cantidadGraduados;

    public Ej3DTOResultado(){
        super();
    }

    public Ej3DTOResultado(String nombreCarrera, int añoInscriptos, int añoGraduados, Long cantidadInscriptos, Long cantidadGraduados) {
        this.nombreCarrera = nombreCarrera;
        this.añoInscriptos = añoInscriptos;
        this.añoGraduados = añoGraduados;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadGraduados = cantidadGraduados;
    }

    @Override
    public String toString() {
        return "{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", añoInscriptos=" + añoInscriptos +
                ", añoGraduados=" + añoGraduados +
                ", cantidadInscriptos=" + cantidadInscriptos +
                ", cantidadGraduados=" + cantidadGraduados +
                '}';
    }
}
