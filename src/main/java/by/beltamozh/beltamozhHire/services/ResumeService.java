package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.dto.ResumeDto;
import by.beltamozh.beltamozhHire.mappers.ResumeMapper;
import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.repositories.ResumeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ResumeService implements CrudService<Resume>, DtoProviderService<ResumeDto> {
    private final ResumeRepository repository;
    private final ResumeMapper mapper;

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
        var resume = repository.findById(id);
        if (resume.isEmpty()) {
            return;
        }
        var resumeToUpdate = resume.get();
        resumeToUpdate.setName(entity.getName());
        resumeToUpdate.setDesiredPosition(entity.getDesiredPosition());
        resumeToUpdate.setSkillLevel(entity.getSkillLevel());
        resumeToUpdate.setDesiredSalary(entity.getDesiredSalary());
        resumeToUpdate.setAbout(entity.getAbout());
        resumeToUpdate.setTechnologies(entity.getTechnologies());
        for (var item : resumeToUpdate.getTechnologies()) {
            if (!item.getResumes().contains(entity)) {
                item.getResumes().add(resumeToUpdate);
            }
        }
        repository.save(resumeToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    //region dto
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

    public Optional<List<ResumeDto>> findAllDtoByOwnerId(int id) {
        var resumes = findAllByOwnerId(id);
        if (resumes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of((List<ResumeDto>) mapper.toIterableDto(resumes.get()));
    }
    //endregion

    public Optional<List<Resume>> findAllByOwnerId(int id) {
        return repository.findAllByOwnerId(id);
    }

    private List<Resume> findAllSorted(int id, String sortBy)
    {
        if (sortBy.equals(""))
        {
            return findAllByOwnerId(id).get();
        }
        return repository.findAllByOwnerId(id, Sort.by(sortBy).ascending());
    }
    private Optional<List<Resume>> findAllByOwnerIdAndName(int id, String name)
    {
        if (name.equals(""))
        {
            return findAllByOwnerId(id);
        }
        return repository.findAllByOwnerIdAndNameContaining(id, name);
    }
    public Optional<List<Resume>> findAllByNameAndOwnerIdSorted(int id, String name, String sortBy) {
        if (name.equals("") && sortBy.equals("")) {
            return findAllByOwnerId(id);
        }
        if (name.equals("") && !sortBy.equals("")) {
            return Optional.ofNullable(findAllSorted(id, sortBy));
        }
        if (!name.equals("") && sortBy.equals("")) {
            return findAllByOwnerIdAndName(id, name);
        }
        return repository.findAllByOwnerIdAndNameContaining(id, name, Sort.by(sortBy).ascending());
    }
}
