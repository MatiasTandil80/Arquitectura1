package Service.DTO.Carrera;


import Entities.Carrera;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CarreraResponseDTO {

    private final Long id;
    private final String nombre;


    public CarreraResponseDTO(Carrera c ) {
        this.id =c.getIdCarrera();
        this.nombre = c.getNombre();
    }

}
