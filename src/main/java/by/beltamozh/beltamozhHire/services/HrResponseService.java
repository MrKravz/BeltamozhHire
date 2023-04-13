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
        return Optional.empty();
    }

    @Override
    public Optional<HrResponse> findById(int id) {
        return Optional.empty();
    }
    @Transactional
    @Override
    public void save(HrResponse value) {

    }
    @Transactional
    @Override
    public void update(HrResponse value, int id) {

    }
    @Transactional
    @Override
    public void delete(int id) {

    }
}
