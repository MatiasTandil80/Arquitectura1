package Application;

import Entities.Alumno;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import Repositories.AlumnoRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class MainApplication {

    private final AlumnoRepository alumnoRepository;


    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }


    @PostConstruct
    public void init() {
        Alumno a1 = new Alumno(12345L,"N1","A1",null,"M","C1");
        Alumno a2 = new Alumno(54321L,"N2","A2",null,"F","C2");

        alumnoRepository.insert(a1);
        alumnoRepository.insert(a2);

    }

    }