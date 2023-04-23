package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.dto.TechnologyDto;
import by.beltamozh.beltamozhHire.mappers.TechnologyMapper;
import by.beltamozh.beltamozhHire.models.Technology;
import by.beltamozh.beltamozhHire.repositories.TechnologyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TechnologyService implements CrudService<Technology>, DtoProviderService<TechnologyDto> {

    private final TechnologyRepository repository;

    private final TechnologyMapper mapper;

    public TechnologyService(TechnologyRepository repository, TechnologyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<Technology>> findAll() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<Technology> findById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void save(Technology entity) {
        repository.save(entity);
    }

    @Override
    @Transactional
    public void update(Technology entity, int id) {
        Optional<Technology> technology = repository.findById(id);
        if (technology.isEmpty()) {
            return;
        }
        Technology technologyToUpdate = technology.get();
        technologyToUpdate.setName(entity.getName());
        technologyToUpdate.setResumes(entity.getResumes());
        technologyToUpdate.setVacancies(entity.getVacancies());
        repository.save(technologyToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<List<TechnologyDto>> findAllDto() {
        var technologies = findAll();
        if (technologies.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of((List<TechnologyDto>) mapper.toIterableDto(technologies.get()));
    }

    @Override
    public Optional<TechnologyDto> findDtoById(int id) {
        var technology = findById(id);
        if (technology.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDto(technology.get()));
    }

    @Override
    public void saveDto(TechnologyDto dto) {
        save(mapper.toEntity(dto));
    }

    @Override
    public void updateDto(TechnologyDto dto, int id) {
        update(mapper.toEntity(dto), id);
    }
}
