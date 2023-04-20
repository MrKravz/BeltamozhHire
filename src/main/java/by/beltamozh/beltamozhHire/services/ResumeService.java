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
    private final TechnologyService service;
    private final ResumeMapper mapper;

    public ResumeService(ResumeRepository repository, TechnologyService service, ResumeMapper mapper) {
        this.repository = repository;
        this.service = service;
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
        var resumeToUpdate = repository.findById(id);
        if (resumeToUpdate.isEmpty()) {
            return;
        }
        var resume = resumeToUpdate.get();
        resume.setName(entity.getName());
        resume.setDesiredPosition(entity.getDesiredPosition());
        resume.setSkillLevel(entity.getSkillLevel());
        resume.setDesiredSalary(entity.getDesiredSalary());
        resume.setAbout(entity.getAbout());
        resume.setTechnologies(entity.getTechnologies());
        for (var item : resume.getTechnologies()) {
            if (!item.getResumes().contains(entity)) {
                item.getResumes().add(resume);
            }
        }
        repository.save(resume);
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

    public Optional<List<Resume>> findAllByOwnerId(int id) {
        return repository.findAllByOwnerId(id);
    }

    public Optional<List<ResumeDto>> findAllDtoByOwnerId(int id) {
        var resumes = findAllByOwnerId(id);
        if (resumes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of((List<ResumeDto>) mapper.toIterableDto(resumes.get()));
    }
}
