package Repositories;

import DTO.Ej3DTOConsulta;
import DTO.Ej3DTOResultado;
import Entities.Alumno;
import Entities.AlumnoCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;



@Repository
public interface AlumnoCarreraRepository extends JpaRepository<AlumnoCarrera, Long> {}

