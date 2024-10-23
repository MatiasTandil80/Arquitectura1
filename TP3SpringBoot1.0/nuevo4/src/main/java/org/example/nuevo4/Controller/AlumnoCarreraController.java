package org.example.nuevo4.Controller;

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
//@RequiredArgsConstructor
public class AlumnoCarreraController {

    @Autowired
    private AlumnoCarreraService alumnoCarreraService;


    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Long nroLibreta, Long dniAlumno, Long idCarrera, LocalDate fechaInscripcion){
        try{                                                                               //Long idAlumnoCarrera, int nroLibreta, Alumno alumno, Carrera carrera, LocalDate fechaInscripcion
            AlumnoCarrera alumnoCarrera = new AlumnoCarrera(nroLibreta, dniAlumno, idCarrera, fechaInscripcion);
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraService.insert(alumnoCarrera));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }


    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody AlumnoCarreraRequestDTO alumnoCarrera){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraService.update(alumnoCarrera));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }


    @GetMapping("")
    public List<AlumnoCarrera> findAll(){

        System.out.println("Bandera Todos");
        List<AlumnoCarrera> alumnoCarreras = alumnoCarreraService.findAll();
        System.out.println(alumnoCarreras);
        return alumnoCarreras; // this.alumnoCarreraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        try{
            AlumnoCarrera alumnoCarrera = alumnoCarreraService.findById(id);
            System.out.println("alumnoCarreraResponseDTO: "+alumnoCarrera.toString());
            System.out.println("Bandera Id Controller Try ");
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarrera);
        }catch (Exception e){
            System.out.println("Bandera Id Controller Catch ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo encontrar, revise el id e intente nuevamente.\"}");
        }



/*        try{
            AlumnoCarreraResponseDTO alumnoCarreraResponseDTO = alumnoCarreraService.findById(id);
            System.out.println("alumnoCarreraResponseDTO: "+alumnoCarreraResponseDTO.toString());
            System.out.println("Bandera Id Controller Try ");
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraResponseDTO);
        }catch (Exception e){
            System.out.println("Bandera Id Controller Catch ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo encontrar, revise el id e intente nuevamente.\"}");
        } */
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alumnoCarreraService.delete(id));
        }catch (Exception e){
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
