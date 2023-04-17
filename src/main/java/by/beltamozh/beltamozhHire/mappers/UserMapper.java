package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.UserDto;
import by.beltamozh.beltamozhHire.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper extends CommonMapper<UserDto, User>{
}
