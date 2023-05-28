package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.dto.CategoryDto;
import by.beltamozh.beltamozhHire.dto.ResumeDto;
import by.beltamozh.beltamozhHire.dto.VacancyDto;
import by.beltamozh.beltamozhHire.mappers.VacancyMapper;
import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.models.Vacancy;
import by.beltamozh.beltamozhHire.repositories.VacancyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class VacancyService implements CrudService<Vacancy>, DtoProviderService<VacancyDto> {

    private final VacancyRepository repository;

    private final VacancyMapper mapper;

    //region crud
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
        Optional<Vacancy> vacancy = repository.findById(id);
        if (vacancy.isEmpty()) {
            return;
        }
        Vacancy vacancyToUpdate = vacancy.get();
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
    //endregion

    //region dto
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
    @Transactional
    public void saveDto(VacancyDto dto) {
        save(mapper.toEntity(dto));
    }

    @Override
    @Transactional
    public void updateDto(VacancyDto dto, int id) {
        update(mapper.toEntity(dto), id);
    }
    //endregion

    private List<Vacancy> findAllSorted(String sortBy)
    {
        if (sortBy.equals(""))
        {
            return findAll().get();
        }
        return repository.findAll(Sort.by(sortBy).ascending());
    }
    private Optional<List<Vacancy>> findAllByName(String name)
    {
        if (name.equals(""))
        {
            return findAll();
        }
        return repository.findAllByNameContaining(name);
    }
    public Optional<List<Vacancy>> findAllByNameSorted(String name, String sortBy) {
        if (name.equals("") && sortBy.equals("")) {
            return findAll();
        }
        if (name.equals("") && !sortBy.equals("")) {
            return Optional.ofNullable(findAllSorted(sortBy));
        }
        if (!name.equals("") && sortBy.equals("")) {
            return findAllByName(name);
        }
        return repository.findAllByNameContaining(name, Sort.by(sortBy).ascending());
    }
}
