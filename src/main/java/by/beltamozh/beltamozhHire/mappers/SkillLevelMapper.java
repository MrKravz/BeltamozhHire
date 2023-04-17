package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.SkillLevelDto;
import by.beltamozh.beltamozhHire.models.SkillLevel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillLevelMapper extends CommonMapper<SkillLevelDto, SkillLevel>{
}
