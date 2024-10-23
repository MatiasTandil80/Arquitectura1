package org.example.nuevo4.Service;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.example.nuevo4.DTO.Ej2f_ResponseDTO;
import org.example.nuevo4.DTO.Ej3DTOConsulta;
import org.example.nuevo4.DTO.Ej3DTOResultado;
import org.example.nuevo4.Entities.Alumno;
import org.example.nuevo4.Entities.AlumnoCarrera;
import org.example.nuevo4.Repositories.AlumnoCarreraRepository;
import org.example.nuevo4.DTO.AlumnoCarrera.AlumnoCarreraRequestDTO;
import org.example.nuevo4.DTO.AlumnoCarrera.AlumnoCarreraResponseDTO;
import lombok.RequiredArgsConstructor;
//import lombok.var;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.example.nuevo4.Exception.NotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlumnoCarreraService {

    @Autowired
    private AlumnoCarreraRepository alumnoCarreraRepository;

    @Transactional
    public AlumnoCarrera insert(AlumnoCarrera alumnoCarrera) {
        //AlumnoCarrera alumnoCarreraResponseDTO = new AlumnoCarreraResponseDTO(new AlumnoCarrera());

        try {
            if (!alumnoCarreraRepository.existsByDniAndIdCarrera(alumnoCarrera.getAlumno().getDni(), alumnoCarrera.getCarrera().getIdCarrera())) {
                //final var alumnoCarrera = new AlumnoCarrera(alumnoCarreraRequestDTO.getFechaInscripcion(), alumnoCarreraRequestDTO.getNroLibreta());
                final var result = alumnoCarreraRepository.save(alumnoCarrera);
                //alumnoCarreraResponseDTO = new AlumnoCarreraResponseDTO(result.getNroLibreta(), result.getAlumno(),
                return result;
            }

        } catch (RuntimeException e) {
            System.out.println("El alumnoCarrera que quiere ingresar ya existe");
        }
        return null;
    }

    @Transactional
    public AlumnoCarreraResponseDTO update(AlumnoCarreraRequestDTO alumnoCarreraRequestDTO) {
        try {
            if (alumnoCarreraRepository.existsById(alumnoCarreraRequestDTO.getIdAlumnoCarrera())) {
                final var alumnoCarrera = new AlumnoCarrera(alumnoCarreraRequestDTO.getFechaInscripcion(), alumnoCarreraRequestDTO.getNroLibreta());
                final var result = this.alumnoCarreraRepository.save(alumnoCarrera);
                return new AlumnoCarreraResponseDTO(result.getNroLibreta(), result.getAlumno(),
                        result.getCarrera());
            }
        } catch (RuntimeException e) {
            System.out.println("El alumnoCarrera que quiere ingresar ya existe");
        }
        return null;
    }


    @Transactional(readOnly = true)
    public List<AlumnoCarrera> findAll() {
        System.out.println("Bandera Service Todo");

        return this.alumnoCarreraRepository.findAll().stream().map(AlumnoCarrera::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlumnoCarrera findById(Long id) {

        System.out.println("Bandera Service Id Ida");
        AlumnoCarrera alumnoCarrera = this.alumnoCarreraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el alumnoCarrera con el id: ", id));
        System.out.println("Bandera Service Id Vuelta");
        return alumnoCarrera;
    }

    //@Override
    @Transactional
    public boolean delete(Long id) throws Exception {

        if (alumnoCarreraRepository.existsById(id)) {
            alumnoCarreraRepository.deleteById(id);
            return true;
        } else {
            System.out.println("El alumno que quiere borrar no existe");
            return false;
        }
    }

    @Transactional(readOnly = true)
    public Alumno findByLibreta(Long nroLibreta) {
        List<Alumno> alumno = this.alumnoCarreraRepository.findByLibreta(nroLibreta);


        if (alumno.isEmpty()) {
            throw new NotFoundException("No existe el alumno con la Libreta: " + nroLibreta);
        }

        return alumno.get(0);
    }

    @Transactional(readOnly = true)
    public List<Ej2f_ResponseDTO> listarCarrerasPorCantidadInscriptos() {
        List<Ej2f_ResponseDTO> ej2f_ResponseDTO = this.alumnoCarreraRepository.listarCarrerasPorCantidadInscriptos();

        if (ej2f_ResponseDTO.isEmpty()) {
            throw new NotFoundException("No existen carreras para listar");
        }

        return ej2f_ResponseDTO;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Estos metodos son del Ej 3

//Este es un metodo privado del Ejercicio 3)
// Retorna todas las cerreras ordenadas alfabeticamente, con la cantidad de inscriptos y graduados por año, ordenado por año

    /////////////////////////PRINCIPAL/////////////////////////////
    //Este es el metodo principal que resuelve el ejercicio 3)

    public List<Ej3DTOResultado> listaCarrerasInscriptosYGraduados() {

        List<Ej3DTOConsulta> listaInscriptos = alumnoCarreraRepository.listaInscriptos();
        List<Ej3DTOConsulta> listaGraduados = alumnoCarreraRepository.listaGraduados();

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
            this.agregarRestante(iteratorGraduados, listaResultado, 'G');
        }
        if (!iteratorGraduados.hasNext()) {
            this.agregarRestante(iteratorInscriptos, listaResultado, 'I');
        }

        return listaResultado;
    }

    /////////////////////////PRIVADO/////////////////////////////
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

    /////////////////////////PRIVADO/////////////////////////////
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

    /////////////////////////PRIVADO/////////////////////////////
    private int comparar(Ej3DTOConsulta a, Ej3DTOConsulta b) {

        int comparacion = (a.getNombreCarrera().compareTo(b.getNombreCarrera()));//Compara los nombres de las carreras

        if (comparacion != 0) {//Los nombres son diferentes
            return comparacion;
        }

        return Integer.compare(a.getAnio(), b.getAnio());

    }
}