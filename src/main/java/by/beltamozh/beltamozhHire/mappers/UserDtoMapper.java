package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.UserDto;
import by.beltamozh.beltamozhHire.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User,UserDto> {

    @Override
    public UserDto apply(User user) {
        return  new UserDto(user.getId(),
                user.getName(),
                user.getLogin(),
                user.getResumes().stream().map(new ResumeDtoMapper()).toList());
    }
}
