package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.dto.UserDto;
import by.beltamozh.beltamozhHire.mappers.UserMapper;
import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.repositories.RoleRepository;
import by.beltamozh.beltamozhHire.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService implements CrudService<User>, DtoProviderService<UserDto> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, RoleRepository roleRepository, UserMapper mapper) {
        this.userRepository = repository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.of(userRepository.findAll());
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(User entity) {
        entity.setRoles(new ArrayList<>(roleRepository.findAll()));
        userRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(User entity, int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return;
        }
        User userToUpdate = user.get();
        userToUpdate.setName(entity.getName());
        userToUpdate.setLogin(entity.getLogin());
        userToUpdate.setPassword(entity.getPassword());
        userToUpdate.setResumes(entity.getResumes());
        userRepository.save(userToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userRepository.deleteUserById(id);
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

    public Optional<User> findByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
}
