package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.dto.ResumeDto;
import by.beltamozh.beltamozhHire.mappers.ResumeMapper;
import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.repositories.ResumeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ResumeService implements CrudService<Resume>, DtoProviderService<ResumeDto> {
    private final ResumeRepository repository;
    private final ResumeMapper mapper;

    public ResumeService(ResumeRepository repository, ResumeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<Resume>> findAll() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<Resume> findById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void save(Resume entity) {
        repository.save(entity);
    }

    @Override
    @Transactional
    public void update(Resume entity, int id) {
        if (repository.findById(id).isEmpty()) {
            return;
        }
        Resume resumeToUpdate = repository.findById(id).get();
        resumeToUpdate.setOwner(entity.getOwner());
        resumeToUpdate.setName(entity.getName());
        resumeToUpdate.setDesiredPosition(entity.getDesiredPosition());
        resumeToUpdate.setSkillLevel(entity.getSkillLevel());
        resumeToUpdate.setDesiredSalary(entity.getDesiredSalary());
        resumeToUpdate.setAbout(entity.getAbout());
        resumeToUpdate.setTechnologies(entity.getTechnologies());
        resumeToUpdate.setCategory(entity.getCategory());
        resumeToUpdate.setHrResponse(entity.getHrResponse());
        repository.save(resumeToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<List<ResumeDto>> findAllDto() {
        var resumes = findAll();
        if (resumes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of((List<ResumeDto>) mapper.toIterableDto(resumes.get()));
    }

    @Override
    public Optional<ResumeDto> findDtoById(int id) {
        var resume = findById(id);
        if (resume.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDto(resume.get()));
    }

    @Override
    public void saveDto(ResumeDto dto) {
        save(mapper.toEntity(dto));
    }

    @Override
    public void updateDto(ResumeDto dto, int id) {
        update(mapper.toEntity(dto), id);
    }

    public Optional<List<Resume>> findAllByOwnerId(int id)
    {
        return repository.findAllByOwnerId(id);
    }

    public Optional<List<ResumeDto>> findAllDtoByOwnerId(int id)
    {
        var resumes = findAllByOwnerId(id);
        if (resumes.isEmpty())
        {
            return Optional.empty();
        }
        return Optional.of((List<ResumeDto>) mapper.toIterableDto(resumes.get()));
    }
}
