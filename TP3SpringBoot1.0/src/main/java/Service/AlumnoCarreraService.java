package Service;
import java.util.stream.Collectors;
import Entities.AlumnoCarrera;
import Repositories.AlumnoCarreraRepository;
import Service.DTO.AlumnoCarrera.AlumnoCarreraRequestDTO;
import Service.DTO.AlumnoCarrera.AlumnoCarreraResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.var;
import Repositories.AlumnoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Exception.NotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlumnoCarreraService {

    private AlumnoCarreraRepository alumnoCarreraRepository;
    private AlumnoCarreraResponseDTO alumnoCarreraResponseDTO;

    @Transactional
    public AlumnoCarreraResponseDTO insert(AlumnoCarreraRequestDTO alumnoCarreraRequestDTO) {


        try {
            if (!alumnoCarreraRepository.existsById(alumnoCarreraRequestDTO.getIdAlumnoCarrera())) {
                final var alumnoCarrera = new AlumnoCarrera(alumnoCarreraRequestDTO.getFechaInscripcion(),alumnoCarreraRequestDTO.getNroLibreta());
                final var result = alumnoCarreraRepository.save(alumnoCarrera);
                alumnoCarreraResponseDTO = new AlumnoCarreraResponseDTO(result.getNroLibreta(), result.getAlumno(),
                        result.getCarrera());
            }

        } catch (RuntimeException e) {
            System.out.println("El alumnoCarrera que quiere ingresar ya existe");
        }
        return alumnoCarreraResponseDTO;
    }

    @Transactional
    public AlumnoCarreraResponseDTO update(AlumnoCarreraRequestDTO alumnoCarreraRequestDTO ){
        try {
            if (alumnoCarreraRepository.existsById(alumnoCarreraRequestDTO.getIdAlumnoCarrera())) {
                final var alumnoCarrera = new AlumnoCarrera(alumnoCarreraRequestDTO.getFechaInscripcion(),alumnoCarreraRequestDTO.getNroLibreta());
                final var result = this.alumnoCarreraRepository.save(alumnoCarrera);
                return new AlumnoCarreraResponseDTO(result.getNroLibreta(), result.getAlumno(),
                        result.getCarrera());
            }
        } catch (RuntimeException e) {
            System.out.println("El alumnoCarrera que quiere ingresar ya existe");
        }
        return null;
    }


    @Transactional( readOnly = true )
    public List<AlumnoCarreraResponseDTO> findAll(){
        return this.alumnoCarreraRepository.findAll().stream().map( AlumnoCarreraResponseDTO::new )
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlumnoCarreraResponseDTO findById(Long id) {
        AlumnoCarrera alumnoCarrera = this.alumnoCarreraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el alumnoCarrera con el id: ", id));
        return new AlumnoCarreraResponseDTO(alumnoCarrera);
    }

    //@Override
    @Transactional
    public boolean delete(Long id) throws Exception {

        if(alumnoCarreraRepository.existsById(id)) {
            alumnoCarreraRepository.deleteById(id);
            return true;
        }else{
            System.out.println("El alumno que quiere borrar no existe");
            return false;
        }


    }
}
