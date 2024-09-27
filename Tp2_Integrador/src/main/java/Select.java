import DTO.Ej3DTOResultado;
import Entities.Alumno;
import repositories.AlumnoCarreraRepository;
import repositories.AlumnoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class Select {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();



        //EJ C)/////////////////////////////////////////////////////////////////////////////////
        @SuppressWarnings("unchecked")
        List<Alumno> alumnos =  new AlumnoRepository(em).listarAlumnosPorApellido();
        System.out.println("Ejercicio C)                             ");
        alumnos.forEach(a -> System.out.println(a.toString()));
        System.out.println("                             ");

        //EJ D)/////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ejercicio D)                             ");
        int numeroLib = 5;
        Alumno alumnoLibreta = new AlumnoCarreraRepository(em).getEstudianteByNroLibreta(numeroLib);

        System.out.println(alumnoLibreta.toString());
        System.out.println("                             ");

        //EJ E)/////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ejercicio E)                             ");

        String genero="Masculino";

        List<Alumno> listaAlumnosPorGenero = new AlumnoRepository(em).listarAlumnosPorGenero(genero);

        listaAlumnosPorGenero.forEach(a -> System.out.println(a.toString()));
        System.out.println("                             ");

        //EJ F)/////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ejercicio F)                             ");

        List<Object[]> carreras = new AlumnoCarreraRepository(em).ListarCarrerasPorCantidadInscriptos();

        carreras.forEach(a -> System.out.println(Arrays.toString(a)));
        System.out.println("Cantidad de Carreras: "+carreras.size());


        System.out.println("  ");
        //EJ G)/////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ejercicio G)                             ");

        String ciudad = "Barcelona";
        String nombreCarrera = "Ingeniería en Sistemas";

        List<Object[]> alumnosCarreraYCiudad = new AlumnoRepository(em).listarAlumnosPorCarreraYCiudad(nombreCarrera,ciudad);

        for (Object[] row : alumnosCarreraYCiudad) {
            Alumno alumno = (Alumno) row[0];    // Primera posición: instancia de Alumno
            String nCarrera = (String) row[1]; // Segunda posición: nombre de la carrera
            System.out.println("Alumno: "+ alumno.toString()+ " Nombre Carrera: "+ nCarrera);
        }

        System.out.println("  ");
        //EJ 3)/////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ejercicio 3)                             ");

            List<Ej3DTOResultado> listaResultado = new AlumnoCarreraRepository(em).listaCarrerasInscriptosYGraduados();

            listaResultado.forEach(a -> System.out.println(a.toString()));

        em.getTransaction().commit();
        em.close();
        emf.close();


    }

}
