package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.dto.SkillLevelDto;
import by.beltamozh.beltamozhHire.mappers.SkillLevelMapper;
import by.beltamozh.beltamozhHire.models.SkillLevel;
import by.beltamozh.beltamozhHire.repositories.SkillLevelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SkillLevelService implements CrudService<SkillLevel>, DtoProviderService<SkillLevelDto> {

    private final SkillLevelRepository repository;
    private final SkillLevelMapper mapper;

    public SkillLevelService(SkillLevelRepository repository, SkillLevelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<SkillLevel>> findAll() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<SkillLevel> findById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void save(SkillLevel entity) {
        repository.save(entity);
    }

    @Override
    @Transactional
    public void update(SkillLevel entity, int id) {
        Optional<SkillLevel> skillLevel = repository.findById(id);
        if (skillLevel.isEmpty()) {
            return;
        }
        SkillLevel skillLevelToUpdate = skillLevel.get();
        skillLevelToUpdate.setName(entity.getName());
        skillLevelToUpdate.setResumes(entity.getResumes());
        skillLevelToUpdate.setVacancies(entity.getVacancies());
        repository.save(skillLevelToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<List<SkillLevelDto>> findAllDto() {
        var skillLevels = findAll();
        if (skillLevels.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of((List<SkillLevelDto>) mapper.toIterableDto(skillLevels.get()));
    }

    @Override
    public Optional<SkillLevelDto> findDtoById(int id) {
        var skillLevel = findById(id);
        if (skillLevel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDto(skillLevel.get()));
    }

    @Override
    public void saveDto(SkillLevelDto dto) {
        save(mapper.toEntity(dto));
    }

    @Override
    public void updateDto(SkillLevelDto dto, int id) {
        update(mapper.toEntity(dto), id);
    }
}
