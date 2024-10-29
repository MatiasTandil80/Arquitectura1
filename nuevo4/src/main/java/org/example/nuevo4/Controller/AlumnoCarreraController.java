package org.example.nuevo4.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.nuevo4.DTO.AlumnoCarrera.AlumnoCarreraControllerDTO;
import org.example.nuevo4.Entities.AlumnoCarrera;
import org.example.nuevo4.Service.AlumnoCarreraService;
import org.example.nuevo4.DTO.AlumnoCarrera.AlumnoCarreraRequestDTO;
import org.example.nuevo4.DTO.AlumnoCarrera.AlumnoCarreraResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/alumnoCarrera")
public class AlumnoCarreraController {

    @Autowired
    private AlumnoCarreraService alumnoCarreraService;


    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody AlumnoCarreraControllerDTO alumnoCarreraControllerDTO){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraService.insert(alumnoCarreraControllerDTO.getNroLibreta(),alumnoCarreraControllerDTO.getDni(),
                    alumnoCarreraControllerDTO.getIdCarrera(),alumnoCarreraControllerDTO.getFechaInscripcion()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }


    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody AlumnoCarreraRequestDTO alumnoCarreraRequestDTO){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraService.update(alumnoCarreraRequestDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }


    @GetMapping("")
    public List<AlumnoCarrera> findAll(){

        List<AlumnoCarrera> alumnoCarreras = alumnoCarreraService.findAll();

        return alumnoCarreras; // this.alumnoCarreraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        try{
            AlumnoCarrera alumnoCarrera = alumnoCarreraService.findById(id);

            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarrera);
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo encontrar, revise el id e intente nuevamente.\"}");
        }


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alumnoCarreraService.delete(id));
        }
        catch (EntityNotFoundException e) {
            // Si el ID no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"No se encontró el alumno con ID " + id + ".\"}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar intente nuevamente.\"}");
        }
    }

/////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/libreta/{nroLibreta}")
    public ResponseEntity<?> findByLibreta(@PathVariable Long nroLibreta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraService.findByLibreta(nroLibreta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo encontrar, revise el nro de Libreta e intente nuevamente.\"}");
        }
    }

    @GetMapping("/carreras")
    public ResponseEntity<?> listarCarrerasPorCantidadInscriptos(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraService.listarCarrerasPorCantidadInscriptos());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No hay carreras para mostrar");
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//// Ej 3
//// Este metodo retorna las carreras ordenadas alfabeticamente, con sus inscriptos y graduados por año ordenados cronologicamente
    @GetMapping("/reporteEj3")
    public ResponseEntity<?> listaCarrerasInscriptosYGraduados(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraService.listaCarrerasInscriptosYGraduados());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No hay carreras para mostrar");
        }
    }

}
