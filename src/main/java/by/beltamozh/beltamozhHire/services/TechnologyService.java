package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.Technology;
import by.beltamozh.beltamozhHire.repositories.TechnologyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TechnologyService implements CrudService<Technology>{

    private final TechnologyRepository repository;

    public TechnologyService(TechnologyRepository repository) {
        this.repository = repository;
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
        if (repository.findById(id).isEmpty()) {
            return;
        }
        Technology technologyToUpdate = repository.findById(id).get();
        technologyToUpdate.setName(entity.getName());
        technologyToUpdate.setResumes(entity.getResumes());
        technologyToUpdate.setVacancies(entity.getVacancies());
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
}
