package Controller;

import Entities.Alumno;
import Service.AlumnoService;
import Service.DTO.Alumno.AlumnoRequestDTO;
import Service.DTO.Alumno.AlumnoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    @Autowired
    private final AlumnoService alumnoService;


    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Alumno alumno){
        try{
            AlumnoRequestDTO alumnoRequestDTO = new AlumnoRequestDTO(alumno.getDni(),alumno.getNombre(),alumno.getApellido());
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.insert(alumnoRequestDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }


    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody AlumnoRequestDTO alumno){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.update(alumno));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }


    @GetMapping("")
    public List<AlumnoResponseDTO> findAll(){
        return this.alumnoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo encontrar, revise el id e intente nuevamente.\"}");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alumnoService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar intente nuevamente.\"}");
        }
    }

    @GetMapping("/alphabetical")
    public List<AlumnoResponseDTO> findAllByAlphabetical(){
        return this.alumnoService.findAllByAlphabetical();
    }


}
