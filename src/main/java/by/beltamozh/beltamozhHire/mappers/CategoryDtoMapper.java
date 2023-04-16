package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.CategoryDto;
import by.beltamozh.beltamozhHire.models.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDtoMapper implements Function<Category, CategoryDto>{

    @Override
    public CategoryDto apply(Category category) {
        return new CategoryDto(category.getId(),
                category.getName());
    }
}
