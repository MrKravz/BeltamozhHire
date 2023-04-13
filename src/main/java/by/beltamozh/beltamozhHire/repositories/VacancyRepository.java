package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
}