package Service.DTO.Carrera;

import Entities.Carrera;
import lombok.Data;
import lombok.RequiredArgsConstructor;

public class CarreraRequestDTO {

    private final Long id;
    private final String nombre;

    public CarreraRequestDTO(Carrera c ) {
        this.id =c.getIdCarrera();
        this.nombre = c.getNombre();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}


