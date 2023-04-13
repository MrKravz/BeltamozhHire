package by.beltamozh.beltamozhHire.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    Optional<List<T>> findAll();

    Optional<T> findById(int id);

    void save(T value);

    void update(T value, int id);

    void delete(int id);
}
