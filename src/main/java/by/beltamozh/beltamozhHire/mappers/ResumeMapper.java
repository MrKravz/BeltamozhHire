package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.ResumeDto;
import by.beltamozh.beltamozhHire.models.Resume;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResumeMapper extends CommonMapper<ResumeDto, Resume>{
}
