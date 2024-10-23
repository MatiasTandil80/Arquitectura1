package org.example.nuevo4.Repositories;

import org.example.nuevo4.DTO.Ej2f_ResponseDTO;
import org.example.nuevo4.DTO.Ej3DTOConsulta;
import org.example.nuevo4.Entities.Alumno;
import org.example.nuevo4.Entities.AlumnoCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


//Este metodo resuelve el punto 2 d)
//Retorna una lista porque la libreta puede estar repetida
@Repository
public interface AlumnoCarreraRepository extends JpaRepository<AlumnoCarrera, Long> {

    @Query ("SELECT CASE WHEN COUNT() > 0 THEN true ELSE false END" +
            " FROM AlumnoCarrera ac WHERE ac.alumno.dni = :dni AND ac.carrera.idCarrera = :idCarrera")
    Boolean existsByDniAndIdCarrera(@Param ("dni") Long dni, @Param("idCarrera") Long idCarrera);

    @Query("SELECT ac.alumno FROM AlumnoCarrera ac WHERE ac.nroLibreta = :numeroLibreta")
    List<Alumno> findByLibreta(@Param("numeroLibreta") Long nroLibreta);


    //Esto resuelve el Ejercicio 2) f)
    //Este metodo retorna un listado de carreras con cantidad de incriptos
    // ordenado por cantidad de inscriptos
    @Query("SELECT c.idCarrera, c.nombre, COUNT(ac) AS cantInscriptos " +
            "FROM AlumnoCarrera ac JOIN ac.carrera c " +
            "GROUP BY c " +
            "ORDER BY cantInscriptos DESC")
    List<Ej2f_ResponseDTO> listarCarrerasPorCantidadInscriptos();


//////////////////////////////////////////////////////////////////////////////////////////////
// Estos metodos son las consultas del Ej 3

//Este es un metodo privado del Ejercicio 3)
// Retorna todas las cerreras ordenadas alfabeticamente, con la cantidad de inscriptos por año, ordenado por año

    @Query(" SELECT c.nombre, year(ac.fechaInscripcion), count(*) " +
            " FROM AlumnoCarrera ac " +
            " JOIN ac.carrera c " +
            " group by c.nombre, year(ac.fechaInscripcion) " +
            " ORDER BY c.nombre, year(ac.fechaInscripcion) ")
    List<Ej3DTOConsulta> listaInscriptos();


    //Este es un metodo privado del Ejercicio 3)
// Retorna todas las cerreras ordenadas alfabeticamente, con la cantidad de graduados por año, ordenado por año


    @Query (" SELECT c.nombre, year(ac.fechaGraduacion), count(*) " +
            " FROM AlumnoCarrera ac " +
            " JOIN ac.carrera c " +
            " WHERE ac.fechaGraduacion IS NOT NULL " +
            " group by c.nombre, year(ac.fechaGraduacion) " +
            " ORDER BY c.nombre, year(ac.fechaGraduacion) ")
    List<Ej3DTOConsulta> listaGraduados();

}
