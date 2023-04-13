package by.beltamozh.beltamozhHire.dto;

import by.beltamozh.beltamozhHire.models.Company;
import by.beltamozh.beltamozhHire.models.SkillLevel;
import by.beltamozh.beltamozhHire.models.Technology;

import java.util.List;

public record ResumeDto(int id,
                        List<Company> companies,
                        String name,
                        String desiredPosition,
                        List<Technology> technologies,
                        SkillLevel skillLevel,
                        float desiredSalary,
                        String about) {
}
