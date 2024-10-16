package Service.DTO.Alumno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class AlumnoRequestDTO {

    @NotNull( message = "El dni es un campo obligatorio.")
    @NotEmpty( message = "El dni es un campo obligatorio.")

    private Long dni;
    private String nombre;
    private String apellido;

    public AlumnoRequestDTO(Long dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }



    public Long getDni(){
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
