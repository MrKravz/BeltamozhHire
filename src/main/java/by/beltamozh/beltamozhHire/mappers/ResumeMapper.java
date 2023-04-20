package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.ResumeDto;
import by.beltamozh.beltamozhHire.models.Resume;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, TechnologyMapper.class, SkillLevelMapper.class})
public interface ResumeMapper extends CommonMapper<ResumeDto, Resume>{
}
