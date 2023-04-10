package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {
}
