package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.dto.CategoryDto;
import by.beltamozh.beltamozhHire.dto.VacancyDto;
import by.beltamozh.beltamozhHire.mappers.VacancyMapper;
import by.beltamozh.beltamozhHire.models.Vacancy;
import by.beltamozh.beltamozhHire.repositories.VacancyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VacancyService implements CrudService<Vacancy>, DtoProviderService<VacancyDto> {
    private final VacancyRepository repository;
    private final VacancyMapper mapper;

    public VacancyService(VacancyRepository repository, VacancyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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

    @Override
    public Optional<List<VacancyDto>> findAllDto() {
        var categories = findAll();
        if (categories.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of((List<VacancyDto>) mapper.toIterableDto(categories.get()));
    }

    @Override
    public Optional<VacancyDto> findDtoById(int id) {
        var category = findById(id);
        if (category.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDto(category.get()));
    }

    @Override
    public void saveDto(VacancyDto dto) {
        save(mapper.toEntity(dto));
    }

    @Override
    public void updateDto(VacancyDto dto, int id) {
        update(mapper.toEntity(dto), id);
    }
}
