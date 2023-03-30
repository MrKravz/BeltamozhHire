package by.beltamozh.beltamozhHire.repositories;

import by.beltamozh.beltamozhHire.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
