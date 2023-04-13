package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.Category;
import by.beltamozh.beltamozhHire.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoryService implements CrudService<Category>{
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<Category>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Category> findById(int id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void save(Category value) {

    }

    @Override
    @Transactional
    public void update(Category value, int id) {

    }

    @Override
    @Transactional
    public void delete(int id) {

    }
}
