import Entities.Alumno;
import Entities.AlumnoCarrera;
import Entities.Carrera;
import repositories.AlumnoCarreraRepository;
import repositories.AlumnoRepository;
import repositories.CarreraRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Insert {

                public static void main(String[] args) {
                        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
                        EntityManager em = emf.createEntityManager();
                        em.getTransaction().begin();

                        // Instancias de Alumno
                        Alumno alumno1 = new Alumno(12345678L, "Juan", "Pérez", LocalDate.of(2000, 1, 15), "Masculino", "Madrid");
                        Alumno alumno2 = new Alumno(87654321L, "Ana", "Gómez", LocalDate.of(2001, 1, 15), "Femenino", "Barcelona");
                        Alumno alumno3 = new Alumno(11223344L, "Carlos", "López", LocalDate.of(1999, 1, 15), "Masculino", "Valencia");
                        Alumno alumno4 = new Alumno(22334455L, "Laura", "Martínez", LocalDate.of(2004, 1, 15), "Femenino", "Sevilla");
                        Alumno alumno5 = new Alumno(33445566L, "Javier", "Ramírez", LocalDate.of(1990, 1, 15), "Masculino", "Bilbao");

                        // Instancias de Carrera
                        Carrera carrera1 = new Carrera("Ingeniería en Sistemas");
                        Carrera carrera2 = new Carrera("Administración de Empresas");
                        Carrera carrera3 = new Carrera("Psicología");
                        Carrera carrera4 = new Carrera("Derecho");
                        Carrera carrera5 = new Carrera("Biología");



                        // Creación de instancias de AlumnoCarrera
                        AlumnoCarrera alumnoCarrera1 = new AlumnoCarrera(LocalDate.of(2021, 1, 15), 1);
                        alumnoCarrera1.setAlumno(alumno1);
                        alumnoCarrera1.setCarrera(carrera1);


                        AlumnoCarrera alumnoCarrera2 = new AlumnoCarrera(LocalDate.of(2020, 2, 20), 2);
                        alumnoCarrera2.setAlumno(alumno1);
                        alumnoCarrera2.setCarrera(carrera2);

                        AlumnoCarrera alumnoCarrera3 = new AlumnoCarrera(LocalDate.of(2017, 3, 10), 3);
                        alumnoCarrera3.setAlumno(alumno2);
                        alumnoCarrera3.setCarrera(carrera3);
                        alumnoCarrera3.setFechaGraduacion(LocalDate.of(2023, 6, 15));


                        AlumnoCarrera alumnoCarrera4 = new AlumnoCarrera(LocalDate.of(2018, 4, 5), 4);
                        alumnoCarrera4.setAlumno(alumno2);
                        alumnoCarrera4.setCarrera(carrera1);
                        alumnoCarrera4.setFechaGraduacion(LocalDate.of(2023, 3, 15)); // Carlos no graduado


                        AlumnoCarrera alumnoCarrera5 = new AlumnoCarrera(LocalDate.of(2015, 5, 25), 5);
                        alumnoCarrera5.setAlumno(alumno3);
                        alumnoCarrera5.setCarrera(carrera4);
                        alumnoCarrera5.setFechaGraduacion(LocalDate.of(2021, 6, 15));

                        AlumnoCarrera alumnoCarrera6 = new AlumnoCarrera(LocalDate.of(2000, 6, 30),5);
                        alumnoCarrera6.setAlumno(alumno3);
                        alumnoCarrera6.setCarrera(carrera5);
                        alumnoCarrera6.setFechaGraduacion(LocalDate.of(2012, 5, 11));


                        AlumnoCarrera alumnoCarrera7 = new AlumnoCarrera(LocalDate.of(2020, 7, 18), 6);
                        alumnoCarrera7.setAlumno(alumno4);
                        alumnoCarrera7.setCarrera(carrera2);


                        AlumnoCarrera alumnoCarrera8 = new AlumnoCarrera(LocalDate.of(2021, 8, 22), 6);
                        alumnoCarrera8.setAlumno(alumno4);
                        alumnoCarrera8.setCarrera(carrera3);

                        AlumnoCarrera alumnoCarrera9 = new AlumnoCarrera(LocalDate.of(2018, 9, 15), 7);
                        alumnoCarrera9.setAlumno(alumno5);
                        alumnoCarrera9.setCarrera(carrera1);

                        AlumnoCarrera alumnoCarrera10 = new AlumnoCarrera(LocalDate.of(1995, 10, 10), 7);
                        alumnoCarrera10.setAlumno(alumno5);
                        alumnoCarrera10.setCarrera(carrera4);
                        alumnoCarrera10.setFechaGraduacion(LocalDate.of(2004, 6, 15));

                        AlumnoCarrera alumnoCarrera11 = new AlumnoCarrera(LocalDate.of(2009, 5, 25), 8);
                        alumnoCarrera11.setAlumno(alumno1);
                        alumnoCarrera11.setCarrera(carrera4);
                        alumnoCarrera11.setFechaGraduacion(LocalDate.of(2015, 6, 15));


                        AlumnoRepository alumnoRep = new AlumnoRepository(em);
                        alumnoRep.saveT(alumno1);
                        alumnoRep.saveT(alumno2);
                        alumnoRep.saveT(alumno3);
                        alumnoRep.saveT(alumno4);
                        alumnoRep.saveT(alumno5);

                        CarreraRepository carreraRep = new CarreraRepository(em);
                        carreraRep.saveT(carrera1);
                        carreraRep.saveT(carrera2);
                        carreraRep.saveT(carrera3);
                        carreraRep.saveT(carrera4);
                        carreraRep.saveT(carrera5);

                        AlumnoCarreraRepository alumnoCarreraRep = new AlumnoCarreraRepository(em);
                        alumnoCarreraRep.saveT(alumnoCarrera1);
                        alumnoCarreraRep.saveT(alumnoCarrera2);
                        alumnoCarreraRep.saveT(alumnoCarrera3);
                        alumnoCarreraRep.saveT(alumnoCarrera4);
                        alumnoCarreraRep.saveT(alumnoCarrera5);
                        alumnoCarreraRep.saveT(alumnoCarrera6);
                        alumnoCarreraRep.saveT(alumnoCarrera7);
                        alumnoCarreraRep.saveT(alumnoCarrera8);
                        alumnoCarreraRep.saveT(alumnoCarrera9);
                        alumnoCarreraRep.saveT(alumnoCarrera10);
                        alumnoCarreraRep.saveT(alumnoCarrera11);

                        em.getTransaction().commit();
                        em.close();
                        emf.close();
                }


}

