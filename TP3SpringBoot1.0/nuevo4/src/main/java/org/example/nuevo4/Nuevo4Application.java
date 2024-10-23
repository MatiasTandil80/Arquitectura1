package org.example.nuevo4;

import org.example.nuevo4.Controller.AlumnoCarreraController;
import org.example.nuevo4.Controller.AlumnoController;
import org.example.nuevo4.Controller.CarreraController;
import org.example.nuevo4.Entities.Alumno;
import org.example.nuevo4.Entities.Carrera;
import org.example.nuevo4.Entities.AlumnoCarrera;
import jakarta.annotation.PostConstruct;
import org.example.nuevo4.Repositories.AlumnoCarreraRepository;
import org.example.nuevo4.Repositories.AlumnoRepository;
import org.example.nuevo4.Repositories.CarreraRepository;
import org.example.nuevo4.Service.AlumnoCarreraService;
import org.example.nuevo4.Service.AlumnoService;
import org.example.nuevo4.Service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
//@ComponentScan(basePackages = "src/main/java/Controller")
//(scanBasePackages = {"src/main/java/Controller"})
//@RequiredArgsConstructor
public class Nuevo4Application {

	@Autowired
	private AlumnoRepository alumnoRepository;
	@Autowired
	private CarreraRepository carreraRepository;
	@Autowired
	private AlumnoCarreraRepository alumnoCarreraRepository;


	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private CarreraService carreraService;
	@Autowired
	private AlumnoCarreraService alumnoCarreraService ;

	@Autowired
	private AlumnoController alumnoController;
	@Autowired
	private CarreraController carreraController;
	@Autowired
	private AlumnoCarreraController alumnoCarreraController;

    public static void main(String[] args) {
		System.out.println("Main");
		SpringApplication.run(Nuevo4Application.class, args);
	}

	@PostConstruct
	public void init() {
		try{
		/* Instancias de Alumno
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
			AlumnoCarrera alumnoCarrera1 = new AlumnoCarrera(LocalDate.of(2021, 1, 15), 1L);
			alumnoCarrera1.setAlumno(alumno1);
			alumnoCarrera1.setCarrera(carrera1);


			AlumnoCarrera alumnoCarrera2 = new AlumnoCarrera(LocalDate.of(2020, 2, 20), 2L);
			alumnoCarrera2.setAlumno(alumno1);
			alumnoCarrera2.setCarrera(carrera2);

			AlumnoCarrera alumnoCarrera3 = new AlumnoCarrera(LocalDate.of(2017, 3, 10), 3L);
			alumnoCarrera3.setAlumno(alumno2);
			alumnoCarrera3.setCarrera(carrera3);
			alumnoCarrera3.setFechaGraduacion(LocalDate.of(2023, 6, 15));


			AlumnoCarrera alumnoCarrera4 = new AlumnoCarrera(LocalDate.of(2018, 4, 5), 4L);
			alumnoCarrera4.setAlumno(alumno2);
			alumnoCarrera4.setCarrera(carrera1);
			alumnoCarrera4.setFechaGraduacion(LocalDate.of(2023, 3, 15)); // Carlos no graduado


			AlumnoCarrera alumnoCarrera5 = new AlumnoCarrera(LocalDate.of(2015, 5, 25), 5L);
			alumnoCarrera5.setAlumno(alumno3);
			alumnoCarrera5.setCarrera(carrera4);
			alumnoCarrera5.setFechaGraduacion(LocalDate.of(2021, 6, 15));

			AlumnoCarrera alumnoCarrera6 = new AlumnoCarrera(LocalDate.of(2000, 6, 30),5L);
			alumnoCarrera6.setAlumno(alumno3);
			alumnoCarrera6.setCarrera(carrera5);
			alumnoCarrera6.setFechaGraduacion(LocalDate.of(2012, 5, 11));


			AlumnoCarrera alumnoCarrera7 = new AlumnoCarrera(LocalDate.of(2020, 7, 18), 6L);
			alumnoCarrera7.setAlumno(alumno4);
			alumnoCarrera7.setCarrera(carrera2);


			AlumnoCarrera alumnoCarrera8 = new AlumnoCarrera(LocalDate.of(2021, 8, 22), 6L);
			alumnoCarrera8.setAlumno(alumno4);
			alumnoCarrera8.setCarrera(carrera3);

			AlumnoCarrera alumnoCarrera9 = new AlumnoCarrera(LocalDate.of(2018, 9, 15), 7L);
			alumnoCarrera9.setAlumno(alumno5);
			alumnoCarrera9.setCarrera(carrera1);

			AlumnoCarrera alumnoCarrera10 = new AlumnoCarrera(LocalDate.of(1995, 10, 10), 7L);
			alumnoCarrera10.setAlumno(alumno5);
			alumnoCarrera10.setCarrera(carrera4);
			alumnoCarrera10.setFechaGraduacion(LocalDate.of(2004, 6, 15));

			AlumnoCarrera alumnoCarrera11 = new AlumnoCarrera(LocalDate.of(2009, 5, 25), 8L);
			alumnoCarrera11.setAlumno(alumno1);
			alumnoCarrera11.setCarrera(carrera4);
			alumnoCarrera11.setFechaGraduacion(LocalDate.of(2015, 6, 15));

			alumnoRepository.save(alumno1);
			alumnoRepository.save(alumno2);
			alumnoRepository.save(alumno3);
			alumnoRepository.save(alumno4);
			alumnoRepository.save(alumno5);

			carreraRepository.save(carrera1);
			carreraRepository.save(carrera2);
			carreraRepository.save(carrera3);
			carreraRepository.save(carrera4);
			carreraRepository.save(carrera5);

			alumnoCarreraRepository.save(alumnoCarrera1);
			alumnoCarreraRepository.save(alumnoCarrera2);
			alumnoCarreraRepository.save(alumnoCarrera3);
			alumnoCarreraRepository.save(alumnoCarrera4);
			alumnoCarreraRepository.save(alumnoCarrera5);
			alumnoCarreraRepository.save(alumnoCarrera6);
			alumnoCarreraRepository.save(alumnoCarrera7);
			alumnoCarreraRepository.save(alumnoCarrera8);
			alumnoCarreraRepository.save(alumnoCarrera9);
			alumnoCarreraRepository.save(alumnoCarrera10);
			alumnoCarreraRepository.save(alumnoCarrera11);




			//CARGO REPOSITORIES
			alumnoRepository.save(alumno1);
			alumnoRepository.save(alumno2);
			alumnoRepository.save(alumno3);
			alumnoRepository.save(alumno4);
			alumnoRepository.save(alumno5);

			carreraRepository.save(carrera1);
			carreraRepository.save(carrera2);
			carreraRepository.save(carrera3);
			carreraRepository.save(carrera4);
			carreraRepository.save(carrera5);

			alumnoCarreraRepository.save(alumnoCarrera1);
			alumnoCarreraRepository.save(alumnoCarrera2);
			alumnoCarreraRepository.save(alumnoCarrera3);
			alumnoCarreraRepository.save(alumnoCarrera4);
			alumnoCarreraRepository.save(alumnoCarrera5);
			alumnoCarreraRepository.save(alumnoCarrera6);
			alumnoCarreraRepository.save(alumnoCarrera7);
			alumnoCarreraRepository.save(alumnoCarrera8);
			alumnoCarreraRepository.save(alumnoCarrera9);
			alumnoCarreraRepository.save(alumnoCarrera10);
			alumnoCarreraRepository.save(alumnoCarrera11);

*/


		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
