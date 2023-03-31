package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers()
    {
        return repository.findAll();
    }

    public User getUserById(int id)
    {
        return repository.getUserById(id);
    }

    @Transactional
    public void save(User user){
        repository.save(user);
    }

    @Transactional
    public void deleteUser(int id)
    {
        repository.deleteUserById(id);
    }
}
