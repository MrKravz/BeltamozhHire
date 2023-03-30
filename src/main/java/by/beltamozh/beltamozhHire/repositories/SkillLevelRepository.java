package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.SkillLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillLevelRepository extends CrudRepository<SkillLevel, Integer> {
}
