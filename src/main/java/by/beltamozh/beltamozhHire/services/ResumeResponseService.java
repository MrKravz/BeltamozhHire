package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.ResumeResponse;
import by.beltamozh.beltamozhHire.repositories.ResumeResponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeResponseService implements CrudService<ResumeResponse>{
    private final ResumeResponseRepository resumeResponseRepository;

    public ResumeResponseService(ResumeResponseRepository resumeResponseRepository) {
        this.resumeResponseRepository = resumeResponseRepository;
    }

    @Override
    public Optional<List<ResumeResponse>> findAll() {
        return Optional.of(resumeResponseRepository.findAll());
    }

    @Override
    public Optional<ResumeResponse> findById(int id) {
        return resumeResponseRepository.findById(id);
    }

    public Optional<ResumeResponse> findByResumeIdAndVacancyId(int resumeId, int vacancyId) {
        return resumeResponseRepository.findByResumeIdAndVacancyId(resumeId, vacancyId);
    }
    public Optional<ResumeResponse> findByResumeId(int id) {
        return resumeResponseRepository.findByResumeId(id);
    }

    @Override
    public void save(ResumeResponse entity) {
        resumeResponseRepository.save(entity);
    }

    @Override
    public void update(ResumeResponse entity, int id) {
        Optional<ResumeResponse> resumeResponse = resumeResponseRepository.findById(id);
        if (resumeResponse.isEmpty()) {
            return;
        }
        ResumeResponse resumeToUpdate = resumeResponse.get();
        resumeToUpdate.setResume(entity.getResume());
        resumeToUpdate.setVacancy(entity.getVacancy());
        resumeToUpdate.setHrResponse(entity.getHrResponse());
        resumeResponseRepository.save(resumeToUpdate);
    }

    @Override
    public void delete(int id) {
        resumeResponseRepository.deleteById(id);
    }
}
