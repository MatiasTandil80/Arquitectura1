package Repositories;

import Entities.Alumno;
import Entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;



@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {}

