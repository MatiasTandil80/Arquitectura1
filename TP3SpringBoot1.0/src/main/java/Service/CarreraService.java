package Service;
import java.util.stream.Collectors;
import Entities.Alumno;
import Entities.Carrera;
import Repositories.CarreraRepository;
import Service.DTO.Alumno.AlumnoRequestDTO;
import Service.DTO.Alumno.AlumnoResponseDTO;
import Service.DTO.Carrera.CarreraRequestDTO;
import Service.DTO.Carrera.CarreraResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.var;
import Repositories.AlumnoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Exception.NotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CarreraService {

    private CarreraRepository carreraRepository;
    private CarreraResponseDTO carreraResponseDTO;

    @Transactional
    public CarreraResponseDTO insert(CarreraRequestDTO carreraRequestDTO) {


        try {
            if (!carreraRepository.existsById(carreraRequestDTO.getId())) {
                final var carrera = new Carrera(carreraRequestDTO.getNombre());
                final var result = this.carreraRepository.save(carrera);
                carreraResponseDTO = new CarreraResponseDTO(result.getIdCarrera(), result.getNombre());
            }

        } catch (RuntimeException e) {
            System.out.println("La carrera que quiere ingresar ya existe");
        }
        return carreraResponseDTO;
    }

    @Transactional
    public CarreraResponseDTO update(CarreraRequestDTO carreraRequestDTO ){
        try {
            if (carreraRepository.existsById(carreraRequestDTO.getId())) {
                final var carrera = new Carrera(carreraRequestDTO.getNombre());
                final var result = this.carreraRepository.save(carrera);
                return new CarreraResponseDTO(result.getIdCarrera(), result.getNombre());
            }
        } catch (RuntimeException e) {
            System.out.println("La carrera que quiere actualizar no existe");
        }
        return null;
    }


    @Transactional( readOnly = true )
    public List<CarreraResponseDTO> findAll(){
        return this.carreraRepository.findAll().stream().map( CarreraResponseDTO::new )
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CarreraResponseDTO findById(Long id) {
        Carrera carrera = this.carreraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe la carrera con el id: ", id));
        return new CarreraResponseDTO(carrera);
    }

    //@Override
    @Transactional
    public boolean delete(Long id) throws Exception {

        if(carreraRepository.existsById(id)) {
            carreraRepository.deleteById(id);
            return true;
        }else{
            System.out.println("La carrera que quiere borrar no existe");
            return false;
        }

    }

}
