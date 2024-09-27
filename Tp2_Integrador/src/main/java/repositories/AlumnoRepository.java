package repositories;

import Entities.Alumno;

import javax.persistence.EntityManager;
import java.util.List;

public class AlumnoRepository implements UniversidadRepository<Alumno> {

    private EntityManager em;

    public AlumnoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Alumno getTById(Long id) {
        return em.find(Alumno.class, id);
    }

    //Este metodo resuelve el ejercicio 2) a) ya que se encarga tanto del insert
    //como del update del alumno

    @Override
    public Alumno saveT(Alumno a) {
        if (this.getTById(Long.valueOf(a.getDni())) == null) {
            em.persist(a);  // insert
        } else {
            a = em.merge(a);    // update
        }
        return a;
    }

    @Override
    public void deleteT(Alumno a) {

        if (em.contains(a)) {
            em.remove(a);
        } else {
            Alumno managedAlumno = em.find(Alumno.class, a.getDni());
            if (managedAlumno != null) {
                em.remove(managedAlumno);
            } else {
                // Maneja el caso en el que el alumno no existe en la base de datos
                System.out.println("El alumno con DNI " + a.getDni() + " no existe.");
            }
        }
    }

    //Este metodo resuelve el Ejerccio 2) c)
    public List<Alumno> listarAlumnosPorApellido() {
        @SuppressWarnings("unchecked")
        List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a ORDER BY a.apellido")
                .getResultList();
        return alumnos;
    }

    //Este metodo resuelve el Ejerccio 2) e)
    public List<Alumno> listarAlumnosPorGenero(String genero) {
        @SuppressWarnings("unchecked")
        List<Alumno> alumnosPorGenero = em.createQuery("SELECT a FROM Alumno a " +
                        "WHERE a.genero =:genero")
                .setParameter("genero", genero)
                .getResultList();
        return alumnosPorGenero;
    }

    //Este metodo resuelve el Ejerccio 2) g)
    public List<Object[]> listarAlumnosPorCarreraYCiudad(String nombreCarrera, String ciudad) {
        List<Object[]> alumnos = em.createQuery(
                        " SELECT a, c.nombre FROM AlumnoCarrera ac " +
                                " JOIN ac.carrera c " +
                                " JOIN ac.alumno a " +
                                " WHERE c.nombre = :nombreCarrera " +
                                " AND a.ciudad = :ciudad "
                )
                .setParameter("nombreCarrera", nombreCarrera)
                .setParameter("ciudad", ciudad)
                .getResultList();
        return alumnos;
    }

}
