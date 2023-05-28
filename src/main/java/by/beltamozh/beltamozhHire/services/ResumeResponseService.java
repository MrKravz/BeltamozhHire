package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.ResumeResponse;
import by.beltamozh.beltamozhHire.repositories.ResumeResponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ResumeResponseService implements CrudService<ResumeResponse>{
    private final ResumeResponseRepository resumeResponseRepository;

    //region crud
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

    public Optional<List<ResumeResponse>> findAllByVacancyId(int id) {
        return resumeResponseRepository.findAllByVacancyId(id);
    }

    @Override
    @Transactional
    public void save(ResumeResponse entity) {
        resumeResponseRepository.save(entity);
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(int id) {
        resumeResponseRepository.deleteById(id);
    }
    //endregion

    public Optional<List<ResumeResponse>> findAllByResumeOwnerId(int id)
    {
        return resumeResponseRepository.findAllByResumeOwnerId(id);
    }
    public Optional<ResumeResponse> findByResumeOwnerIdAndVacancyId(int resumeOwnerId, int vacancyId)
    {
        return resumeResponseRepository.findByResumeOwnerIdAndVacancyId(resumeOwnerId, vacancyId);
    }
}
