package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
