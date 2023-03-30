package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.HrResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrResponseRepository extends CrudRepository<HrResponse, Integer> {
}
