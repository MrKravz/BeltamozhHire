package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.Vacancy;
import by.beltamozh.beltamozhHire.repositories.VacancyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VacancyService implements CrudService<Vacancy> {
    private final VacancyRepository repository;

    public VacancyService(VacancyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<Vacancy>> findAll() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void save(Vacancy entity) {
        repository.save(entity);
    }

    @Override
    @Transactional
    public void update(Vacancy entity, int id) {
        if (repository.findById(id).isEmpty()) {
            return;
        }
        Vacancy vacancyToUpdate = repository.findById(id).get();
        vacancyToUpdate.setName(entity.getName());
        vacancyToUpdate.setAbout(entity.getAbout());
        vacancyToUpdate.setTechnologies(entity.getTechnologies());
        vacancyToUpdate.setEstimatedSalary(entity.getEstimatedSalary());
        vacancyToUpdate.setRequiredSkillLevel(entity.getRequiredSkillLevel());
        vacancyToUpdate.setRequiredWorkingExperience(entity.getRequiredWorkingExperience());
        repository.save(vacancyToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
}
