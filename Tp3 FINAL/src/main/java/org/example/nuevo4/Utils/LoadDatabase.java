package org.example.nuevo4.Utils;

import org.example.nuevo4.Entities.*;

import lombok.extern.slf4j.Slf4j;
import org.example.nuevo4.Repositories.AlumnoCarreraRepository;
import org.example.nuevo4.Repositories.AlumnoRepository;
import org.example.nuevo4.Repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@Slf4j
class LoadDatabase {

    private Alumno alumno1, alumno2, alumno3, alumno4, alumno5;
    private Carrera carrera1, carrera2, carrera3, carrera4, carrera5;

    @Bean
    CommandLineRunner initDatabase(@Qualifier("AlumnoRepository") AlumnoRepository alumnoRepository,
                                   @Qualifier("CarreraRepository") CarreraRepository carreraRepository,
                                   @Qualifier("AlumnoCarreraRepository") AlumnoCarreraRepository alumnoCarreraRepository) {
        return args -> {

        alumno1 = new Alumno(12345678L, "Juan", "Pérez",
                LocalDate.of(2000, 1, 15), "Masculino", "Madrid");
        alumno2 = new Alumno(87654321L, "Ana", "Gómez", LocalDate.of(2001, 1, 15), "Femenino", "Barcelona");
        alumno3 = new Alumno(11223344L, "Carlos", "López", LocalDate.of(1999, 1, 15), "Masculino", "Valencia");
        alumno4 = new Alumno(22334455L, "Laura", "Martínez", LocalDate.of(2004, 1, 15), "Femenino", "Sevilla");
        alumno5 = new Alumno(33445566L, "Javier", "Ramírez", LocalDate.of(1990, 1, 15), "Masculino", "Bilbao");

            log.info("Preloading " + alumnoRepository.save(alumno1));
            log.info("Preloading " + alumnoRepository.save(alumno2));
            log.info("Preloading " + alumnoRepository.save(alumno3));
            log.info("Preloading " + alumnoRepository.save(alumno4));
            log.info("Preloading " + alumnoRepository.save(alumno5));

            carrera1 = new Carrera("Ingeniería en Sistemas");
            carrera2 = new Carrera("Administración de Empresas");
            carrera3 = new Carrera("Psicología");
            carrera4 = new Carrera("Derecho");
            carrera5 = new Carrera("Biología");

            log.info("Preloading " + carreraRepository.save(carrera1));
            log.info("Preloading " + carreraRepository.save(carrera2));
            log.info("Preloading " + carreraRepository.save(carrera3));
            log.info("Preloading " + carreraRepository.save(carrera4));
            log.info("Preloading " + carreraRepository.save(carrera5));

AlumnoCarrera ac1 =new AlumnoCarrera(2L, alumno1, carrera2, LocalDate.of(2020, 2, 20));
ac1.setFechaGraduacion(LocalDate.of(2020, 2, 20));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(1L, alumno1, carrera1, LocalDate.of(2021, 1, 15))));
            log.info("Preloading " + alumnoCarreraRepository.save(ac1));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(3L, alumno2, carrera3, LocalDate.of(2017, 3, 10))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(4L, alumno2, carrera1, LocalDate.of(2018, 4, 5))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(5L, alumno3, carrera4, LocalDate.of(2015, 5, 25))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(6L, alumno3, carrera5, LocalDate.of(2000, 6, 30))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(7L, alumno4, carrera2, LocalDate.of(2020, 7, 18))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(8L, alumno4, carrera3, LocalDate.of(2021, 8, 22))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(9L, alumno5, carrera1, LocalDate.of(2018, 9, 15))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(10L, alumno5, carrera4, LocalDate.of(1995, 10, 10))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(11L, alumno1, carrera4, LocalDate.of(2009, 5, 25))));



        };
    }

/*
    @Bean
    CommandLineRunner initDatabase2(@Qualifier("CarreraRepository") CarreraRepository carreraRepository) {
        return args -> {

            carrera1 = new Carrera("Ingeniería en Sistemas");
            carrera2 = new Carrera("Administración de Empresas");
            carrera3 = new Carrera("Psicología");
            carrera4 = new Carrera("Derecho");
            carrera5 = new Carrera("Biología");

            log.info("Preloading " + carreraRepository.save(carrera1));
            log.info("Preloading " + carreraRepository.save(carrera2));
            log.info("Preloading " + carreraRepository.save(carrera3));
            log.info("Preloading " + carreraRepository.save(carrera4));
            log.info("Preloading " + carreraRepository.save(carrera5));


        };
    }

    @Bean
    CommandLineRunner initDatabase3(@Qualifier("AlumnoCarreraRepository") AlumnoCarreraRepository alumnoCarreraRepository) {
        return args -> {


            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(1L, alumno1, carrera1, LocalDate.of(2021, 1, 15))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(2L, alumno1, carrera2, LocalDate.of(2020, 2, 20))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(3L, alumno2, carrera3, LocalDate.of(2017, 3, 10))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(4L, alumno2, carrera1, LocalDate.of(2018, 4, 5))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(5L, alumno3, carrera4, LocalDate.of(2015, 5, 25))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(6L, alumno3, carrera5, LocalDate.of(2000, 6, 30))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(7L, alumno4, carrera2, LocalDate.of(2020, 7, 18))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(8L, alumno4, carrera3, LocalDate.of(2021, 8, 22))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(9L, alumno5, carrera1, LocalDate.of(2018, 9, 15))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(10L, alumno5, carrera4, LocalDate.of(1995, 10, 10))));
            log.info("Preloading " + alumnoCarreraRepository.save(new AlumnoCarrera(11L, alumno1, carrera4, LocalDate.of(2009, 5, 25))));


        };
    }



*/


}