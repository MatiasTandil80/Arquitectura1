package dao;

public interface CrudDAO<T> {

    void insert(T t);
    void update(T t);
    T getById(int id);
    T getById(int id1, int id2);
    void delete(int id);
    void delete(int id1, int id2);


}
