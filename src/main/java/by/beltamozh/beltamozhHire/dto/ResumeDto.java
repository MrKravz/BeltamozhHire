package by.beltamozh.beltamozhHire.dto;

import by.beltamozh.beltamozhHire.models.*;
import lombok.*;

import java.util.List;

@Data
public class ResumeDto{

    private int id;

    private List<Company> companies;

    private String name;

    private User owner;

    private String desiredPosition;

    private List<Technology> technologies;

    private SkillLevel skillLevel;

    private float desiredSalary;

    private String about;

}
