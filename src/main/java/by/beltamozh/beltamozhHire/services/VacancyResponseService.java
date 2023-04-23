package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.dto.HrResponseDto;
import by.beltamozh.beltamozhHire.dto.ResumeDto;
import by.beltamozh.beltamozhHire.dto.VacancyDto;
import by.beltamozh.beltamozhHire.mappers.HrResponseMapper;
import by.beltamozh.beltamozhHire.mappers.ResumeMapper;
import by.beltamozh.beltamozhHire.mappers.VacancyMapper;
import org.springframework.stereotype.Service;

@Service
public class VacancyResponseService {
    private final VacancyMapper vacancyMapper;
    private final ResumeMapper resumeMapper;
    private final HrResponseMapper hrResponseMapper;

    public VacancyResponseService(VacancyMapper vacancyMapper, ResumeMapper resumeMapper, HrResponseMapper hrResponseMapper) {
        this.vacancyMapper = vacancyMapper;
        this.resumeMapper = resumeMapper;
        this.hrResponseMapper = hrResponseMapper;
    }

    public void addResponse(VacancyDto vacancyDto, ResumeDto resumeDto)
    {
        vacancyMapper.toEntity(vacancyDto).getResumes().add(resumeMapper.toEntity(resumeDto));
    }

    public void respondToResponse(ResumeDto resumeDto, HrResponseDto hrResponseDto)
    {
        resumeMapper.toEntity(resumeDto).setHrResponse(hrResponseMapper.toEntity(hrResponseDto));
    }
}
