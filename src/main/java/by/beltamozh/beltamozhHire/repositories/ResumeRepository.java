package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume,Integer> {
    Resume getResumeById(int id);
    List<Resume> getResumeByOwnerId(int id);
    void deleteResumeById(int id);
}
