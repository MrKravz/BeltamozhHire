package by.beltamozh.beltamozhHire.dto;

import lombok.*;

import java.util.List;

@Data
public class TechnologyDto{

    private int id;

    private String name;

    @ToString.Exclude
    private List<ResumeDto> technologiesResumes;

}
