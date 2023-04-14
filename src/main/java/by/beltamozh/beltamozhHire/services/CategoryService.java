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
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<Category> findById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void save(Category entity) {
        repository.save(entity);
    }

    @Override
    @Transactional
    public void update(Category entity, int id) {
        if (repository.findById(id).isEmpty()) {
            return;
        }
        Category categoryToUpdate = repository.findById(id).get();
        categoryToUpdate.setName(entity.getName());
        categoryToUpdate.setResumes(entity.getResumes());
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
}
