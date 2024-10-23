package org.example.nuevo4.Controller;

import org.example.nuevo4.Entities.Alumno;
import org.example.nuevo4.Service.AlumnoService;
import org.example.nuevo4.DTO.Alumno.AlumnoRequestDTO;
import org.example.nuevo4.DTO.Alumno.AlumnoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno")
//@RequiredArgsConstructor
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;


    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Alumno alumno){
        System.out.println("Entro al alumnoController.insert");
        try{
            System.out.println("Entro al try");
            //AlumnoRequestDTO alumnoRequestDTO = new AlumnoRequestDTO(alumno.getDni(),alumno.getNombre(),alumno.getApellido());
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.insert(alumno));
        }catch (Exception e){
            System.out.println("Entro al catch");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }


    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Alumno alumno){
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
        System.out.println("Bandera 1");
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo encontrar, revise el id e intente nuevamente.\"}");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            System.out.println("Id: " +id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alumnoService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar intente nuevamente.\"}");
        }
    }

//// Ej 2 c)
////Este metodo retorna un listado de alumnos ordenado alfabeticamente por apellido y nombre
    @GetMapping("/alphabetical")
    public List<AlumnoResponseDTO> findAllByAlphabetical(){
        return this.alumnoService.findAllByAlphabetical();
    }


    /**
    *
    *
    * */
//// Ej 2 e)
//// Este metodo retorna los alumnos de un determinado genero
    @GetMapping("/genero/{genero}")
    public List<AlumnoResponseDTO> findByGender(@PathVariable String genero){
        return this.alumnoService.findByGender(genero);
    }
//// Ej 2 g)
//// Este metodo retorna los alumnos de una determinada carrera y ciudad
    @GetMapping("/carrera_ciudad/{carrera}/{ciudad}")
    public List<Alumno> listarAlumnosPorCarreraYCiudad(@PathVariable String carrera, @PathVariable String ciudad){
        return this.alumnoService.listarAlumnosPorCarreraYCiudad(carrera, ciudad);
    }

}
