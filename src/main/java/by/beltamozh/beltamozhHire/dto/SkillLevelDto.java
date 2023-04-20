package by.beltamozh.beltamozhHire.dto;

import lombok.*;

import java.util.List;

@Data
public class SkillLevelDto{
    int id;
    String name;
    @ToString.Exclude
    List<ResumeDto> skillLevelsResumes;
}
