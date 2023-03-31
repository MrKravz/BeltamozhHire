package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserById(Integer id);
    User deleteUserById(Integer id);
}
