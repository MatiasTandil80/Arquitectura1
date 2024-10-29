package org.example.nuevo4.Controller;

import org.example.nuevo4.Entities.Carrera;
import org.example.nuevo4.Service.CarreraService;
import org.example.nuevo4.DTO.Carrera.CarreraRequestDTO;
import org.example.nuevo4.DTO.Carrera.CarreraResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;


    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Carrera carrera){

        try{
            CarreraRequestDTO carreraRequestDTO = new CarreraRequestDTO(carrera);
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.insert(carreraRequestDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }


    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Carrera carrera){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.update(carrera));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }


    @GetMapping("")
    public List<CarreraResponseDTO> findAll(){
        return this.carreraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo encontrar, revise el id e intente nuevamente.\"}");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(carreraService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar intente nuevamente.\"}");
        }
    }

}
