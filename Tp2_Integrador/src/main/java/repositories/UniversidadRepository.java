package repositories;

public interface UniversidadRepository<T> {

    T getTById(Long id); // Devuelve un objeto de tipo T
    T saveT(T t); // Devuelve el objeto guardado de tipo T
    void deleteT(T t); // No devuelve nada, solo elimina el objeto de tipo T

}
