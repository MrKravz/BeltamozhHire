package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.HrResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrResponseRepository extends JpaRepository<HrResponse, Integer> {
}
