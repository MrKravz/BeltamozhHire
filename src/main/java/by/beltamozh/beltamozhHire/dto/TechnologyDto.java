package by.beltamozh.beltamozhHire.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class TechnologyDto{
    int id;
    String name;
    @ToString.Exclude
    List<ResumeDto> technologiesResumes;
}
