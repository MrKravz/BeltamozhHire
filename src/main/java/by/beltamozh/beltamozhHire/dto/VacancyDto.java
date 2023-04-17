package by.beltamozh.beltamozhHire.dto;

import by.beltamozh.beltamozhHire.models.SkillLevel;
import by.beltamozh.beltamozhHire.models.Technology;

import java.util.List;

public record VacancyDto(int id,
                         String name,
                         String about,
                         float estimatedSalary,
                         SkillLevelDto requiredSkillLevel,
                         int requiredWorkingExperience,
                         List<TechnologyDto> technologies) {
}
