package by.beltamozh.beltamozhHire.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T>  {
    Optional<List<T>> findAll();

    Optional<T> findById(int id);

    void save(T entity);

    default void update(T entity, int id) {
        save(entity);
    }

    void delete(int id);
}
