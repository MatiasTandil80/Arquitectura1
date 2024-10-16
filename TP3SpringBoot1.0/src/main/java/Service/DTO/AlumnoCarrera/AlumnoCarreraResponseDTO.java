package Service.DTO.AlumnoCarrera;

import Entities.Alumno;
import Entities.AlumnoCarrera;
import Entities.Carrera;

public class AlumnoCarreraResponseDTO {

    private int nroLibreta;
    private Alumno alumno;
    private Carrera carrera;

    public AlumnoCarreraResponseDTO(int nroLibreta, Alumno alumno, Carrera carrera) {
        this.nroLibreta = nroLibreta;
        this.alumno = alumno;
        this.carrera = carrera;
    }
    public AlumnoCarreraResponseDTO(AlumnoCarrera alumnoCarrera) {
        this.nroLibreta = alumnoCarrera.getNroLibreta();
        this.alumno = alumnoCarrera.getAlumno();
        this.carrera = alumnoCarrera.getCarrera();
    }
}
