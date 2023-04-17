package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.VacancyDto;
import by.beltamozh.beltamozhHire.models.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VacancyMapper extends CommonMapper<VacancyDto, Vacancy>{
}
