package by.beltamozh.beltamozhHire.services;

import java.util.List;
import java.util.Optional;

public interface DtoProviderService<T> {

    Optional<List<T>> findAllDto();

    Optional<T> findDtoById(int id);

    void saveDto(T dto);

    void updateDto(T dto, int id);
}
