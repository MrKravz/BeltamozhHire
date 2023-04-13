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
        return Optional.empty();
    }

    @Override
    public Optional<Technology> findById(int id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void save(Technology value) {

    }

    @Override
    @Transactional
    public void update(Technology value, int id) {

    }

    @Override
    @Transactional
    public void delete(int id) {

    }
}
