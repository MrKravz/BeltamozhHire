package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.models.Vacancy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    List<Vacancy> findAll(Sort sort);
    Optional<List<Vacancy>> findAllByNameContaining(String name);
    Optional<List<Vacancy>> findAllByNameContaining(String name, Sort sort);
}
