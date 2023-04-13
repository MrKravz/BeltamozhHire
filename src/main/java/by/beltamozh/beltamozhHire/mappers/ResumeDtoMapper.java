package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.ResumeDto;
import by.beltamozh.beltamozhHire.models.Resume;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ResumeDtoMapper implements Function<Resume, ResumeDto> {

    @Override
    public ResumeDto apply(Resume resume) {
        return new ResumeDto(resume.getId(),
                resume.getCompanies(),
                resume.getName(),
                resume.getDesiredPosition(),
                resume.getTechnologies(),
                resume.getSkillLevel(),
                resume.getDesiredSalary(),
                resume.getAbout());
    }
}
