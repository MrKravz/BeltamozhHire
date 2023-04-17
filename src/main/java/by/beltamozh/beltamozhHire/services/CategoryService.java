package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.dto.CategoryDto;
import by.beltamozh.beltamozhHire.mappers.CategoryMapper;
import by.beltamozh.beltamozhHire.models.Category;
import by.beltamozh.beltamozhHire.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoryService implements CrudService<Category>, DtoProviderService<CategoryDto> {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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

    @Override
    public Optional<List<CategoryDto>> findAllDto() {
        var categories = findAll();
        if (categories.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of((List<CategoryDto>) mapper.toIterableDto(categories.get()));
    }

    @Override
    public Optional<CategoryDto> findDtoById(int id) {
        var category = findById(id);
        if (category.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDto(category.get()));
    }

    @Override
    public void saveDto(CategoryDto dto) {
        save(mapper.toEntity(dto));
    }

    @Override
    public void updateDto(CategoryDto dto, int id) {
        update(mapper.toEntity(dto), id);
    }
}
