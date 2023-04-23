package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.SkillLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillLevelRepository extends JpaRepository<SkillLevel, Integer> {
}
