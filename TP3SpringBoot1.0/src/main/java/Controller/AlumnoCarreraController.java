package Controller;

import Entities.Alumno;
import Entities.AlumnoCarrera;
import Service.AlumnoCarreraService;
import Service.AlumnoService;
import Service.DTO.Alumno.AlumnoRequestDTO;
import Service.DTO.Alumno.AlumnoResponseDTO;
import Service.DTO.AlumnoCarrera.AlumnoCarreraRequestDTO;
import Service.DTO.AlumnoCarrera.AlumnoCarreraResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alumnos")
@RequiredArgsConstructor
public class AlumnoCarreraController {

    @Autowired
    private final AlumnoCarreraService alumnoCarreraService;


    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody AlumnoCarrera alumnoCarrera){
        try{                                                                               //Long idAlumnoCarrera, int nroLibreta, Alumno alumno, Carrera carrera, LocalDate fechaInscripcion
            AlumnoCarreraRequestDTO alumnoCarreraRequestDTO = new AlumnoCarreraRequestDTO(alumnoCarrera.getIdAlumnoCarrera(),alumnoCarrera.getNroLibreta(),alumnoCarrera.getAlumno(),alumnoCarrera.getCarrera(),alumnoCarrera.getFechaInscripcion());
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraService.insert(alumnoCarreraRequestDTO));
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
    public List<AlumnoCarreraResponseDTO> findAll(){
        return this.alumnoCarreraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo encontrar, revise el id e intente nuevamente.\"}");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alumnoCarreraService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar intente nuevamente.\"}");
        }
    }

}
