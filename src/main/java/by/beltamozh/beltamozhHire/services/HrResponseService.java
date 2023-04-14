package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.HrResponse;
import by.beltamozh.beltamozhHire.repositories.HrResponseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HrResponseService implements CrudService<HrResponse> {

    private final HrResponseRepository repository;

    public HrResponseService(HrResponseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<HrResponse>> findAll() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<HrResponse> findById(int id) {
        return repository.findById(id);
    }
    @Transactional
    @Override
    public void save(HrResponse entity) {
        repository.save(entity);
    }
    @Transactional
    @Override
    public void update(HrResponse entity, int id) {
        if (repository.findById(id).isEmpty()) {
            return;
        }
        HrResponse hrResponseToUpdate = repository.findById(id).get();
        hrResponseToUpdate.setName(entity.getName());
        hrResponseToUpdate.setResumes(entity.getResumes());
        repository.save(entity);
    }
    @Transactional
    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
