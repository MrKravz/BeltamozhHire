package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.SkillLevel;
import by.beltamozh.beltamozhHire.repositories.SkillLevelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SkillLevelService implements CrudService<SkillLevel>{

    private final SkillLevelRepository repository;

    public SkillLevelService(SkillLevelRepository repository) {
        this.repository = repository;
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
        if (repository.findById(id).isEmpty()) {
            return;
        }
        SkillLevel skillLevelToUpdate = repository.findById(id).get();
        skillLevelToUpdate.setName(entity.getName());
        skillLevelToUpdate.setResumes(entity.getResumes());
        skillLevelToUpdate.setVacancies(entity.getVacancies());
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
}
