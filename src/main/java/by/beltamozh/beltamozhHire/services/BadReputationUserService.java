package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.BadReputationUser;
import by.beltamozh.beltamozhHire.repositories.BadReputationUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BadReputationUserService implements CrudService<BadReputationUser> {

    private final BadReputationUserRepository repository;

    public BadReputationUserService(BadReputationUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<BadReputationUser>> findAll() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<BadReputationUser> findById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void save(BadReputationUser entity) {
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
}
