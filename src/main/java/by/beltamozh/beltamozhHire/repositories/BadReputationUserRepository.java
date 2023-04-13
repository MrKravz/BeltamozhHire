package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.BadReputationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadReputationUserRepository extends JpaRepository<BadReputationUser,Integer>{
}
