package by.beltamozh.beltamozhHire.dto;

import by.beltamozh.beltamozhHire.models.Company;
import by.beltamozh.beltamozhHire.models.SkillLevel;
import by.beltamozh.beltamozhHire.models.Technology;

import java.util.List;

public record ResumeDto(int id,
                        List<Company> companies,
                        String name,

                        UserDto owner,
                        String desiredPosition,
                        List<TechnologyDto> technologies,
                        SkillLevelDto skillLevel,
                        float desiredSalary,
                        String about) {
}
