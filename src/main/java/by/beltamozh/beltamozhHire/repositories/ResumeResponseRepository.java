package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.models.ResumeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeResponseRepository extends JpaRepository<ResumeResponse, Integer> {
    Optional<ResumeResponse> findByResumeIdAndVacancyId(int resumeId, int vacancyId);
    Optional<List<ResumeResponse>> findAllByVacancyId(int id);
}
