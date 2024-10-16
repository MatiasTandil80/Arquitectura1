package app;

import Repositories.AlumnoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MappingRelationsApplication {
    private AlumnoRepository alumnoRepository;


    public static void main(String[] args) {
        System.out.println("Main");
        SpringApplication.run(MappingRelationsApplication.class, args);
    }

    @PostConstruct
    public void init() {


    }
}
