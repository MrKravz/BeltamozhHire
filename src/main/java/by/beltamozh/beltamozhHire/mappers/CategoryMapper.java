package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.CategoryDto;
import by.beltamozh.beltamozhHire.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper extends CommonMapper<CategoryDto, Category>{
}
