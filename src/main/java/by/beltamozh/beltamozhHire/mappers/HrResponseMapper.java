package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.HrResponseDto;
import by.beltamozh.beltamozhHire.models.HrResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HrResponseMapper extends CommonMapper<HrResponseDto, HrResponse>{
}
