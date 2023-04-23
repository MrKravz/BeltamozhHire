package by.beltamozh.beltamozhHire.dto;

import lombok.*;

import java.util.List;

@Data
public class SkillLevelDto{
    private int id;

    private String name;

    @ToString.Exclude
    private List<ResumeDto> skillLevelsResumes;
}
