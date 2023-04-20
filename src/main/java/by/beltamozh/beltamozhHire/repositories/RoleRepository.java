package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
