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
        return Optional.of(repository.getUserById(id));
    }

    @Override
    @Transactional
    public void save(User user){
        repository.save(user);
    }

    @Override
    @Transactional
    public void update(User user, int id){
        User userToUpdate = repository.getUserById(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setLogin(user.getLogin());
        userToUpdate.setPassword(user.getPassword());
        repository.save(userToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id)
    {
        repository.deleteUserById(id);
    }
}
