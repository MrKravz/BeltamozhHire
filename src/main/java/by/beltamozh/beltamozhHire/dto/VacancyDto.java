package by.beltamozh.beltamozhHire.dto;

import by.beltamozh.beltamozhHire.models.*;
import lombok.Data;

import java.util.List;
@Data
public class VacancyDto{

    private int id;

    private String name;

    private String about;

    private float estimatedSalary;

    private SkillLevel requiredSkillLevel;

    private int requiredWorkingExperience;

    private List<Technology> technologies;

}
