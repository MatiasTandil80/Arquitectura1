package org.example.nuevo4.Service;
import java.util.stream.Collectors;

import org.example.nuevo4.Entities.Carrera;
import org.example.nuevo4.Repositories.CarreraRepository;
import org.example.nuevo4.DTO.Carrera.CarreraRequestDTO;
import org.example.nuevo4.DTO.Carrera.CarreraResponseDTO;
import lombok.RequiredArgsConstructor;
//import lombok.var;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.example.nuevo4.Exception.NotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Transactional
    public Carrera insert(CarreraRequestDTO carreraRequestDTO) {
       //CarreraResponseDTO carreraResponseDTO = new CarreraResponseDTO(new Carrera(carreraRequestDTO.getNombre()));

        try {
            if (!carreraRepository.existsCarreraByNombre(carreraRequestDTO.getNombre())) {
                final var carrera = new Carrera(carreraRequestDTO.getNombre());
                final var result = this.carreraRepository.save(carrera);
                //carreraResponseDTO = new CarreraResponseDTO(result.getIdCarrera(), result.getNombre());
                return result;
            }

        } catch (RuntimeException e) {
            System.out.println("La carrera que quiere ingresar ya existe");
        }
        return null;
    }

    @Transactional
    public Carrera update(Carrera carrera){
        System.out.println( "IdCarrera: " + carrera.getIdCarrera());
        try {
            if (carreraRepository.existsById(carrera.getIdCarrera())) {
                //final var carrera = new Carrera(carrera.getNombre());
                final var result = this.carreraRepository.save(carrera);
                return result;
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
    public Carrera findById(Long id) {
        System.out.println("El idCarrera es " + id);
        Carrera carrera = this.carreraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe la carrera con el id: ", id));
        System.out.println(carrera);
        return carrera;
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
