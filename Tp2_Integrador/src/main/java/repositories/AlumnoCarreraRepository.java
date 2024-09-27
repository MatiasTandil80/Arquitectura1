package repositories;

import DTO.Ej3DTOConsulta;
import DTO.Ej3DTOResultado;
import Entities.Alumno;
import Entities.AlumnoCarrera;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class AlumnoCarreraRepository implements UniversidadRepository<AlumnoCarrera> {

    private EntityManager em;

    public AlumnoCarreraRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public AlumnoCarrera getTById(Long id) {

        return em.find(AlumnoCarrera.class, id);
    }

    //Este metodo resuelve el punto 2) b)
    //Matricular un estudiante
    @Override
    public AlumnoCarrera saveT(AlumnoCarrera ac) {

        if (ac.getNroLibreta() == 0) {
            em.persist(ac);
        } else {
            ac = em.merge(ac);
        }
        return ac;

    }

    @Override
    public void deleteT(AlumnoCarrera ac) {
        if (em.contains(ac)) {
            em.remove(ac);
        } else {
            AlumnoCarrera managedAlumnoCarrera = em.find(AlumnoCarrera.class, ac.getNroLibreta());
            if (managedAlumnoCarrera != null) {
                em.remove(managedAlumnoCarrera);
            } else {
                // Maneja el caso en el que el AlumnoCarrera no exista en la base de datos
                System.out.println("El AlumnoCarrera con Nro Libreta " + ac.getNroLibreta() + " no existe.");
            }
        }
    }

    // Este metodo resuelve el Ejerccio 2) d)
    // Esta consulta podria retornar mas de un registro alumnoCarrera, cuando un alumno
    // esté cursando mas de una carrera de la misma facultad
    // Retornamos el alumno del primer registro, porque si hubiera mas de uno, serian
    // siempre del mismo alumno
    public Alumno getEstudianteByNroLibreta(int nroLibreta) {
        List<Alumno> alumnos = em.createQuery("SELECT ac.alumno FROM AlumnoCarrera ac " +
                        "WHERE ac.nroLibreta = :numeroLibreta")
                .setParameter("numeroLibreta", nroLibreta)
                .getResultList();
        return alumnos.get(0);

    }

    //Esto resuelve el Ejercicio 2) f)
    public List<Object[]> ListarCarrerasPorCantidadInscriptos() {
        @SuppressWarnings("unchecked")
        List<Object[]> carreras = em.createQuery
                        ("SELECT c.nombre," +
                                " COUNT(*) AS cantInscriptos " +
                                " FROM AlumnoCarrera ac JOIN ac.carrera c" +
                                " GROUP BY ac.carrera " +
                                "ORDER BY cantInscriptos DESC")
                .getResultList();
        return carreras;
    }

    //Este es un metodo privado del Ejercicio 3)
    private List<Ej3DTOConsulta> listaInscriptos() {

        List<Object[]> listaCarrerasInscripcion = em.createQuery(
                " SELECT c.nombre, year(ac.fechaInscripcion), count(*) " +
                        " FROM AlumnoCarrera ac " +
                        " JOIN ac.carrera c " +
                        " group by c.nombre, year(ac.fechaInscripcion) " +
                        " ORDER BY c.nombre, year(ac.fechaInscripcion) "
        ).getResultList();


        return this.convertirLista(listaCarrerasInscripcion);

    }

    //Este es un metodo privado del Ejercicio 3)
    private List<Ej3DTOConsulta> listaGraduados() {

        List<Object[]> listaCarrerasGraduacion = em.createQuery(
                " SELECT c.nombre, year(ac.fechaGraduacion), count(*) " +
                        " FROM AlumnoCarrera ac " +
                        " JOIN ac.carrera c " +
                        " WHERE ac.fechaGraduacion IS NOT NULL " +
                        " group by c.nombre, year(ac.fechaGraduacion) " +
                        " ORDER BY c.nombre, year(ac.fechaGraduacion) "
        ).getResultList();

        return this.convertirLista(listaCarrerasGraduacion);

    }

    //Este es un metodo privado del Ejercicio 3)
    //Convierte la lista de Object[] de la consulta en List<Ej3DTOConsulta>
    private List<Ej3DTOConsulta> convertirLista(List<Object[]> listaCarreras) {
        List<Ej3DTOConsulta> listaConsulta = new ArrayList();

        listaCarreras.forEach(a -> {
            Ej3DTOConsulta consulta = new Ej3DTOConsulta((String) a[0], (int) a[1], (Long) a[2]);
            listaConsulta.add(consulta);
        });
        return listaConsulta;
    }

    //Este es un metodo privado del Ejercicio 3)
    //Este metodo agrega el resto de la lista a la resultante cuando se acabó la otra lista
    private List<Ej3DTOResultado> agregarRestante(ListIterator<Ej3DTOConsulta> iteratorRestante, List<Ej3DTOResultado> resultado, char bandera) {

        if (bandera == 'I') {
            while (iteratorRestante.hasNext()) {
                Ej3DTOConsulta elementoInscriptos = iteratorRestante.next();
                String nombreCarreraInscriptos = elementoInscriptos.getNombreCarrera();
                int anioInscriptos = elementoInscriptos.getAnio();
                Long cantidadInscriptos = elementoInscriptos.getCantidad();
                Ej3DTOResultado elemInscriptoDTO = new Ej3DTOResultado(nombreCarreraInscriptos,
                        anioInscriptos, 0, cantidadInscriptos, null);
                resultado.add(elemInscriptoDTO);

            }

        } else {
            if (bandera == 'G') {
                while (iteratorRestante.hasNext()) {
                    Ej3DTOConsulta elementoGraduados = iteratorRestante.next();
                    String nombreCarreraGraduados = elementoGraduados.getNombreCarrera();
                    int anioGraduados = elementoGraduados.getAnio();
                    Long cantidadGraduados = elementoGraduados.getCantidad();
                    Ej3DTOResultado elemGraduadoDTO = new Ej3DTOResultado(nombreCarreraGraduados,
                            0, anioGraduados, null, cantidadGraduados);
                    resultado.add(elemGraduadoDTO);

                }
            }
        }

        return resultado;

    }

    //Este es el metodo principal que resuelve el ejercicio 3)
    public List<Ej3DTOResultado> listaCarrerasInscriptosYGraduados() {

        List<Ej3DTOConsulta> listaInscriptos = this.listaInscriptos();
        List<Ej3DTOConsulta> listaGraduados = this.listaGraduados();

        List<Ej3DTOResultado> listaResultado = new ArrayList<Ej3DTOResultado>();

        ListIterator<Ej3DTOConsulta> iteratorInscriptos = listaInscriptos.listIterator();
        ListIterator<Ej3DTOConsulta> iteratorGraduados = listaGraduados.listIterator();

        while (iteratorInscriptos.hasNext() && iteratorGraduados.hasNext()) {

            Ej3DTOConsulta elementoInscriptos = iteratorInscriptos.next();
            Ej3DTOConsulta elementoGraduados = iteratorGraduados.next();
            String nombreCarreraInscriptos = elementoInscriptos.getNombreCarrera();
            String nombreCarreraGraduados = elementoGraduados.getNombreCarrera();
            int anioInscriptos = elementoInscriptos.getAnio();
            int anioGraduados = elementoGraduados.getAnio();
            Long cantidadInscriptos = elementoInscriptos.getCantidad();
            Long cantidadGraduados = elementoGraduados.getCantidad();
            Ej3DTOResultado nuevoDTO;

            int a = comparar(elementoInscriptos, elementoGraduados);
            if (a == 0) {
                nuevoDTO = new Ej3DTOResultado(nombreCarreraInscriptos,
                        anioInscriptos, anioGraduados, cantidadInscriptos, cantidadGraduados);
                listaResultado.add(nuevoDTO);
            }

            if (a > 0) {
                nuevoDTO = new Ej3DTOResultado(nombreCarreraGraduados,
                        0, anioGraduados, null, cantidadGraduados);
                listaResultado.add(nuevoDTO);
                iteratorInscriptos.previous();
            }
            if (a < 0) {
                nuevoDTO = new Ej3DTOResultado(nombreCarreraInscriptos,
                        anioInscriptos, 0, cantidadInscriptos, null);
                listaResultado.add(nuevoDTO);
                iteratorGraduados.previous();
            }

        }//end del while

        if (!iteratorInscriptos.hasNext()) {
            this.agregarRestante(iteratorInscriptos, listaResultado, 'G');
        }
        if (!iteratorGraduados.hasNext()) {
            this.agregarRestante(iteratorGraduados, listaResultado, 'I');
        }


        return listaResultado;
    }

    private int comparar(Ej3DTOConsulta a, Ej3DTOConsulta b) {

        int comparacion = (a.getNombreCarrera().compareTo(b.getNombreCarrera()));//Compara los nombres de las carreras

        if (comparacion != 0) {//Los nombres son diferentes
            return comparacion;
        }

        return Integer.compare(a.getAnio(), b.getAnio());

    }


}



