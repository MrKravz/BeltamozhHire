package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.Resume;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    Optional<List<Resume>> findAllByOwnerId(int id);
    Optional<Resume> findByName(String name);
    List<Resume> findAllByOwnerId(int id, Sort sort);
    Optional<List<Resume>> findAllByOwnerIdAndNameContaining(int id, String name);
    Optional<List<Resume>> findAllByOwnerIdAndNameContaining(int id, String name, Sort sort);
}
