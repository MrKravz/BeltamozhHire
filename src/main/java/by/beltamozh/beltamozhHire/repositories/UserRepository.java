package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserById(Integer id);
    User getUserByName(String name);
    User deleteUserById(Integer id);
}
