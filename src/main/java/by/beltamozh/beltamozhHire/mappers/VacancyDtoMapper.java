package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.VacancyDto;
import by.beltamozh.beltamozhHire.models.Vacancy;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class VacancyDtoMapper implements Function<Vacancy, VacancyDto> {
    @Override
    public VacancyDto apply(Vacancy vacancy) {
        return new VacancyDto(
                vacancy.getId(),
                vacancy.getName(),
                vacancy.getAbout(),
                vacancy.getEstimatedSalary(),
                vacancy.getRequiredSkillLevel(),
                vacancy.getRequiredWorkingExperience(),
                vacancy.getTechnologies());
    }
}