package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService implements CrudService<User> {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<User>> findAll()
    {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<User> findById(int id)
    {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void save(User entity){
        repository.save(entity);
    }

    @Override
    @Transactional
    public void update(User entity, int id){
        User userToUpdate = repository.getUserById(id);
        userToUpdate.setName(entity.getName());
        userToUpdate.setLogin(entity.getLogin());
        userToUpdate.setPassword(entity.getPassword());
        userToUpdate.setResumes(entity.getResumes());
        repository.save(userToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id)
    {
        repository.deleteUserById(id);
    }
}
