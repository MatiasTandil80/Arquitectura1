package org.example.nuevo4.Service;
import java.util.stream.Collectors;
import org.example.nuevo4.Entities.Alumno;
import org.example.nuevo4.DTO.Alumno.AlumnoResponseDTO;
import org.example.nuevo4.Repositories.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.example.nuevo4.Exception.NotFoundException;

import java.util.List;


@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Transactional
    public Alumno insert(Alumno alumno) {

        try {
            if (!alumnoRepository.existsById(alumno.getDni())) {

                final var result = alumnoRepository.save(alumno);

                return result;

            }
            else {
                System.out.println("El alumno que quiere ingresar ya existe");
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    public Alumno update(Alumno alumno){
        try {
            if (alumnoRepository.existsById(alumno.getDni())) {

                final var result = this.alumnoRepository.save(alumno);
                return alumno;

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
    public Alumno findById(Long id) {

        Alumno alumno = this.alumnoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el alumno con el id: ", id));

        return alumno;
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Transactional( readOnly = true )
    public List<AlumnoResponseDTO> findAllByAlphabetical(){

        List<AlumnoResponseDTO> alumnoResponseDTOS = alumnoRepository.findAllByAlphabetical().stream().map( AlumnoResponseDTO::new )
                .collect(Collectors.toList());

        if (alumnoResponseDTOS.isEmpty()) {
            throw new NotFoundException("No existen alumnos para listar");
        }

        return alumnoResponseDTOS;

    }

    @Transactional( readOnly = true )
    public List<AlumnoResponseDTO> findByGender(String genero){

        List<AlumnoResponseDTO> alumnoResponseDTOS = alumnoRepository.findByGender(genero).stream().map( AlumnoResponseDTO::new )
                .collect(Collectors.toList());

        if (alumnoResponseDTOS.isEmpty()) {
            throw new NotFoundException("No existen alumnos para listar");
        }

        return alumnoResponseDTOS;
    }


    @Transactional( readOnly = true )
    public List<Alumno> listarAlumnosPorCarreraYCiudad(String carrera, String genero){

        List<Alumno> alumnos = alumnoRepository.listarAlumnosPorCarreraYCiudad(carrera, genero).stream().map( Alumno::new )
                .collect(Collectors.toList());

        if (alumnos.isEmpty()) {
            throw new NotFoundException("No existen alumnos para listar");
        }

        return alumnos;
    }

}
