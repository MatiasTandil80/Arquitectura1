package org.example.nuevo4.Repositories;

import org.example.nuevo4.Entities.Alumno;
import org.example.nuevo4.Entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Carrera c WHERE c.nombre = :nombre")
    Boolean existsCarreraByNombre(@Param("nombre") String nombre);

}

