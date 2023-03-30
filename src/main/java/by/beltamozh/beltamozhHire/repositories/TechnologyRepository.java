package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.Technology;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends CrudRepository<Technology, Integer> {
}
