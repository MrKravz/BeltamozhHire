package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.TechnologyDto;
import by.beltamozh.beltamozhHire.models.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TechnologyMapper extends CommonMapper<TechnologyDto, Technology>{
}
