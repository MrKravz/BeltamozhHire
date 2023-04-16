package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.SkillLevelDto;
import by.beltamozh.beltamozhHire.models.SkillLevel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SkillLevelDtoMapper implements Function<SkillLevel, SkillLevelDto> {
    @Override
    public SkillLevelDto apply(SkillLevel skillLevel) {
        return new SkillLevelDto(skillLevel.getId(),
                skillLevel.getName());
    }
}
