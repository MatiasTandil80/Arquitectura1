package org.example.nuevo4.Repositories;

import org.example.nuevo4.Entities.Alumno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    //Esto consulta resuelve el ejercicio 2 c)
    //Retorna una lista completa de alumnos ordenada por abecedario
    @Query ("SELECT a FROM Alumno a ORDER BY a.apellido ASC, a.nombre ASC")
    List<Alumno> findAllByAlphabetical();

    //Esta consulta resuelve el ejercicio 2 e)
    //Retorna una lista de alumnos por genero
    @Query("SELECT a FROM Alumno a WHERE a.genero = :genero")
    List<Alumno> findByGender(@Param("genero") String genero);


    //Este metodo resuelve el Ejerccio 2) g)

    @Query(" SELECT a" +
            " FROM AlumnoCarrera ac " +
            " JOIN ac.carrera c " +
            " JOIN ac.alumno a " +
            " WHERE c.nombre = :nombreCarrera " +
            " AND a.ciudad = :ciudad ")
    List<Alumno> listarAlumnosPorCarreraYCiudad(@Param ("nombreCarrera") String nombreCarrera, @Param ("ciudad") String ciudad);

}
