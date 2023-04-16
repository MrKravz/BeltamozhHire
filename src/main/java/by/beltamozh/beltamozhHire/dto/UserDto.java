package by.beltamozh.beltamozhHire.dto;

import java.util.List;

public record UserDto(int id, String name, String login, List<ResumeDto> resumes) {
}
