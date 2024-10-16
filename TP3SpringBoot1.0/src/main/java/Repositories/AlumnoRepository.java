package Repositories;

import Entities.Alumno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query ("SELECT a FROM Alumno a ORDER BY a.apellido ASC, a.nombre ASC")
    List<Alumno> findAllByAlphabetical();

}
