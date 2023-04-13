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
        return Optional.empty();
    }

    @Override
    public Optional<SkillLevel> findById(int id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void save(SkillLevel value) {

    }

    @Override
    @Transactional
    public void update(SkillLevel value, int id) {

    }

    @Override
    @Transactional
    public void delete(int id) {

    }
}
