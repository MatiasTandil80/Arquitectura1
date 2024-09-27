package repositories;

import Entities.Alumno;
import Entities.Carrera;

import javax.persistence.EntityManager;

public class CarreraRepository implements UniversidadRepository<Carrera> {

    private EntityManager em;

    public CarreraRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Carrera getTById(Long id) {
        return em.find(Carrera.class, id);
    }

    @Override
    public Carrera saveT(Carrera c) {
        if (c.getIdCarrera() == 0) {
            em.persist(c);
        } else {
            c = em.merge(c);
        }
        return c;
    }

    @Override
    public void deleteT(Carrera c) {
        if (em.contains(c)) {
            em.remove(c);
        } else {
            Carrera managedCarrera = em.find(Carrera.class, c.getIdCarrera());
            if (managedCarrera != null) {
                em.remove(managedCarrera);
            }else {
                // Maneja el caso en el que la carrera no exista en la base de datos
                System.out.println("La carrera con ID " + c.getIdCarrera() + " no existe.");
            }
        }
    }
}
