package Service;
import java.util.stream.Collectors;
import Entities.Alumno;
import Service.DTO.Alumno.AlumnoRequestDTO;
import Service.DTO.Alumno.AlumnoResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.var;
import Repositories.AlumnoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Exception.NotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlumnoService {

    private AlumnoRepository alumnoRepository;
    private AlumnoResponseDTO alumnoResponseDTO;

    @Transactional
    public AlumnoResponseDTO insert(AlumnoRequestDTO alumnoRequestDTO) {


        try {
            if (!alumnoRepository.existsById(alumnoRequestDTO.getDni())) {
                final var alumno = new Alumno(alumnoRequestDTO);
                final var result = alumnoRepository.save(alumno);
                alumnoResponseDTO = new AlumnoResponseDTO(result.getDni(), result.getNombre(),
                        result.getApellido());
            }

        } catch (RuntimeException e) {
            System.out.println("El alumno que quiere ingresar ya existe");
        }
        return alumnoResponseDTO;
    }

    @Transactional
    public AlumnoResponseDTO update(AlumnoRequestDTO request ){
        try {
            if (alumnoRepository.existsById(request.getDni())) {
                final var alumno = new Alumno(request);
                final var result = this.alumnoRepository.save(alumno);
                return new AlumnoResponseDTO(result.getDni(), result.getNombre(),
                        result.getApellido());
            }
        } catch (RuntimeException e) {
            System.out.println("El alumno que quiere ingresar ya existe");
        }
        return null;
    }


    @Transactional( readOnly = true )
    public List<AlumnoResponseDTO> findAll(){
        return this.alumnoRepository.findAll().stream().map( AlumnoResponseDTO::new )
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlumnoResponseDTO findById(Long id) {
        Alumno alumno = this.alumnoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el alumno con el id: ", id));
        return new AlumnoResponseDTO(alumno);
    }

    //@Override
    @Transactional
    public boolean delete(Long id) throws Exception {

            if(alumnoRepository.existsById(id)) {
                alumnoRepository.deleteById(id);
                return true;
            }else{
                System.out.println("El alumno que quiere borrar no existe");
                return false;
            }
    }

    @Transactional( readOnly = true )
    public List<AlumnoResponseDTO> findAllByAlphabetical(){
        return this.alumnoRepository.findAllByAlphabetical().stream().map( AlumnoResponseDTO::new )
                .collect(Collectors.toList());
    }

}
