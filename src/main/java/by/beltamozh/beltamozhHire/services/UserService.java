package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.dto.UserDto;
import by.beltamozh.beltamozhHire.mappers.UserMapper;
import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService implements CrudService<User>, DtoProviderService<UserDto> {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void save(User entity) {
        repository.save(entity);
    }

    @Override
    @Transactional
    public void update(User entity, int id) {
        User userToUpdate = repository.getUserById(id);
        userToUpdate.setName(entity.getName());
        userToUpdate.setLogin(entity.getLogin());
        userToUpdate.setPassword(entity.getPassword());
        userToUpdate.setResumes(entity.getResumes());
        repository.save(userToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteUserById(id);
    }

    @Override
    public Optional<List<UserDto>> findAllDto() {
        var users = findAll();
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of((List<UserDto>) mapper.toIterableDto(users.get()));
    }

    @Override
    public Optional<UserDto> findDtoById(int id) {
        var user = findById(id);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDto(user.get()));
    }

    @Override
    public void saveDto(UserDto dto) {
        save(mapper.toEntity(dto));
    }

    @Override
    public void updateDto(UserDto dto, int id) {
        update(mapper.toEntity(dto), id);
    }
}
